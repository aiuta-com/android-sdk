package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.utils

import android.content.Context
import android.net.Uri
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.operations.GeneratedOperationFactory
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.warmup.WarmUpInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.LastSavedImages
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.SourceImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.imageSource
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.size
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperation
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationOperation
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.toOperation
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.activateGeneration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.deactivateGeneration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.showErrorState
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationUriContainer
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationUrlContainer
import java.util.concurrent.atomic.AtomicInteger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal fun FashionTryOnController.startGeneration(
    aiutaConfiguration: AiutaTryOnConfiguration,
    context: Context,
) {
    generationScope.launch {
        activateGeneration()

        val errorCount = AtomicInteger()

        val rawGenerationFlows = solveStartGenerationFlows(context = context)
        val generationFlows =
            rawGenerationFlows.mapIndexed { index, generationFlow ->
                generationFlow
                    .onEach { status ->
                        // Save generations for history, if operation is success and history available
                        if (status is SKUGenerationStatus.SuccessGenerationStatus && aiutaConfiguration.isHistoryAvailable) {
                            generatedImageInteractor.insertAll(status.imageUrls)
                        }
                    }
                    .mapNotNull { status ->
                        lastSavedImages.value.imageSource.getOrNull(index)?.let { sourceImage ->
                            status.toOperation(sourceImage = sourceImage)
                        }
                    }
                    .catch {
                        if (errorCount.incrementAndGet() == lastSavedImages.value.size) {
                            generationStatus.value = SKUGenerationUIStatus.IDLE
                            deactivateGeneration()
                            showErrorState()
                        }
                    }
            }

        generationFlows
            .merge()
            .cancellable()
            .collect { operation -> solveOperationCollecting(operation) }
    }
}

private fun FashionTryOnController.solveStartGenerationFlows(
    context: Context,
): List<Flow<SKUGenerationStatus>> {
    return when (val activeImages = lastSavedImages.value) {
        is LastSavedImages.UriSource -> {
            startGenerationWithUriSource(context = context, uriSource = activeImages)
        }

        is LastSavedImages.UrlSource -> {
            startGenerationWithUrlSource(urlSource = activeImages)
        }

        is LastSavedImages.Empty -> emptyList()
    }
}

private fun FashionTryOnController.startGenerationWithUriSource(
    context: Context,
    uriSource: LastSavedImages.UriSource,
): List<Flow<SKUGenerationStatus>> {
    val generatedOperationFactory = GeneratedOperationFactory(generatedOperationInteractor)
    val warmUpInteractor = WarmUpInteractor(context)

    return uriSource.imageUris.map { uri ->
        aiutaTryOn()
            .startSKUGeneration(
                container =
                    SKUGenerationUriContainer(
                        fileUri = Uri.parse(uri),
                        skuId = activeSKUItem.value.skuId,
                        skuCatalogName = activeSKUItem.value.catalogName,
                    ),
            )
            .onEach { status ->
                if (status is SKUGenerationStatus.LoadingGenerationStatus.UploadedSourceImage) {
                    val currentOperationId =
                        generatedOperationFactory.getOperationId(
                            imageId = status.sourceImageId,
                        )

                    generatedOperationInteractor.createImage(
                        status = status,
                        operationId = currentOperationId,
                    )

                    if (lastSavedOperation.value?.operationId != currentOperationId) {
                        val images =
                            listOf(
                                SourceImage(
                                    imageId = status.sourceImageId,
                                    imageUrl = status.sourceImageUrl,
                                ),
                            )

                        // Warm up for smooth change
                        warmUpInteractor.warmUp(status.sourceImageUrl)

                        // Change to new
                        lastSavedImages.value = LastSavedImages.UrlSource(sourceImages = images)
                        lastSavedOperation.value =
                            GeneratedOperation(
                                operationId = currentOperationId,
                                sourceImages = images,
                            )
                    }
                }
            }
    }
}

private fun FashionTryOnController.startGenerationWithUrlSource(
    urlSource: LastSavedImages.UrlSource,
): List<Flow<SKUGenerationStatus>> {
    return urlSource.sourceImages.map { sourceImage ->
        aiutaTryOn()
            .startSKUGeneration(
                container =
                    SKUGenerationUrlContainer(
                        fileId = sourceImage.imageId,
                        fileUrl = sourceImage.imageUrl,
                        skuId = activeSKUItem.value.skuId,
                        skuCatalogName = activeSKUItem.value.catalogName,
                    ),
            )
    }
}

// Collecting
private fun FashionTryOnController.solveOperationCollecting(operation: SKUGenerationOperation) {
    when (operation) {
        is SKUGenerationOperation.LoadingOperation -> {
            if (generationStatus.value != SKUGenerationUIStatus.SUCCESS) {
                generationStatus.value = SKUGenerationUIStatus.LOADING
            }

            // Check is this operation already exist
            // Should think about optimization for O(n)
            val existedOperation =
                generationOperations.find { it.sourceImage == operation.sourceImage }
            if (existedOperation == null) {
                generationOperations.add(operation)
            }
        }

        is SKUGenerationOperation.SuccessOperation -> {
            generalScope.launch {
                // Try warm up first
                addSuccessGenerations(operation)

                // Then move to result screen
                generationStatus.value = SKUGenerationUIStatus.SUCCESS
                refreshOperation(operation)
            }
        }

        is SKUGenerationOperation.ErrorOperation -> {
            showErrorState()
            refreshOperation(operation)
        }
    }
}

private fun FashionTryOnController.refreshOperation(newOperation: SKUGenerationOperation) {
    generationOperations.forEachIndexed { index, oldOperation ->
        if (oldOperation.sourceImage == newOperation.sourceImage) {
            generationOperations[index] = newOperation
        }
    }
}

private suspend fun FashionTryOnController.addSuccessGenerations(
    newOperation: SKUGenerationOperation.SuccessOperation,
) {
    sessionGenerationInteractor.addGenerations(newOperation.generatedImageUrls)
}

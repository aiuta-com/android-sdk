package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.utils

import android.content.Context
import android.net.Uri
import com.aiuta.fashionsdk.internal.analytic.model.StartTryOnEvent
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.operations.GeneratedOperationFactory
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.warmup.WarmUpInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.LastSavedImages
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.SourceImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.imageSource
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.size
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperationUIModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationOperation
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.toOperation
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendErrorDownloadResultEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendStartEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendSuccessTryOnEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.TryOnToastErrorState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.activateGeneration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.deactivateGeneration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.dialog.AiutaTryOnDialogController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.dialog.AiutaTryOnDialogState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.dialog.hideDialog
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.dialog.showDialog
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateTo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.showErrorState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.updateActiveOperationOrSetEmpty
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.updateActiveOperationWithFirstOrSetEmpty
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationUriContainer
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationUrlContainer
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.isTryOnGenerationAbortedException
import java.util.concurrent.atomic.AtomicInteger
import kotlin.time.measureTimedValue
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
    dialogController: AiutaTryOnDialogController,
    stringResources: InternalAiutaTryOnLanguage,
    // Analytic
    origin: StartTryOnEvent.TryOnOrigin,
) {
    generationScope.launch {
        activateGeneration()

        sendStartEvent(origin)

        val errorCount = AtomicInteger()
        val generatedOperationFactory = GeneratedOperationFactory(generatedOperationInteractor)

        val rawGenerationFlows =
            solveStartGenerationFlows(
                generatedOperationFactory = generatedOperationFactory,
            )
        val generationFlows =
            rawGenerationFlows.mapIndexed { index, generationFlow ->
                generationFlow
                    .mapNotNull { status ->
                        lastSavedImages.value.imageSource.getOrNull(index)?.let { sourceImage ->
                            status.toOperation(sourceImage = sourceImage)
                        }
                    }
                    .catch {
                        if (errorCount.incrementAndGet() == lastSavedImages.value.size) {
                            generationStatus.value = SKUGenerationUIStatus.IDLE
                            deactivateGeneration()
                        }
                    }
            }

        generationFlows
            .merge()
            .cancellable()
            .collect { operation ->
                solveOperationCollecting(
                    aiutaConfiguration = aiutaConfiguration,
                    context = context,
                    dialogController = dialogController,
                    operation = operation,
                    stringResources = stringResources,
                    generatedOperationFactory = generatedOperationFactory,
                )
            }
    }
}

private fun FashionTryOnController.solveStartGenerationFlows(
    generatedOperationFactory: GeneratedOperationFactory,
): List<Flow<SKUGenerationStatus>> {
    return when (val activeImages = lastSavedImages.value) {
        is LastSavedImages.UriSource -> {
            startGenerationWithUriSource(
                uriSource = activeImages,
                generatedOperationFactory = generatedOperationFactory,
            )
        }

        is LastSavedImages.UrlSource -> {
            startGenerationWithUrlSource(urlSource = activeImages)
        }

        is LastSavedImages.Empty -> emptyList()
    }
}

private fun FashionTryOnController.startGenerationWithUriSource(
    uriSource: LastSavedImages.UriSource,
    generatedOperationFactory: GeneratedOperationFactory,
): List<Flow<SKUGenerationStatus>> {
    return uriSource.imageUris.map { uri ->
        aiutaTryOn
            .startSKUGeneration(
                container =
                    SKUGenerationUriContainer(
                        fileUri = Uri.parse(uri),
                        skuId = activeSKUItem.value.skuId,
                        skuCatalogName = activeSKUItem.value.catalogName,
                    ),
            )
            .onEach { status ->
                // Notify (or create locally) about new operation, after success
                // Also need update active operation, if we change local URI to backend URL
                if (status is SKUGenerationStatus.SuccessGenerationStatus) {
                    val currentOperationId =
                        generatedOperationFactory.getOperationId(
                            imageId = status.sourceImageId,
                        )

                    generatedOperationInteractor.createImage(
                        sourceImageId = status.sourceImageId,
                        sourceImageUrl = status.sourceImageUrl,
                        operationId = currentOperationId,
                    )
                }
            }
    }
}

private fun FashionTryOnController.startGenerationWithUrlSource(
    urlSource: LastSavedImages.UrlSource,
): List<Flow<SKUGenerationStatus>> {
    return urlSource.sourceImages.map { sourceImage ->
        aiutaTryOn
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
private suspend fun FashionTryOnController.solveOperationCollecting(
    aiutaConfiguration: AiutaTryOnConfiguration,
    context: Context,
    dialogController: AiutaTryOnDialogController,
    operation: SKUGenerationOperation,
    stringResources: InternalAiutaTryOnLanguage,
    generatedOperationFactory: GeneratedOperationFactory,
) {
    when (operation) {
        is SKUGenerationOperation.LoadingOperation -> {
            if (generationStatus.value != SKUGenerationUIStatus.SUCCESS) {
                generationStatus.value = SKUGenerationUIStatus.LOADING
            }

            // Check is this operation already exist
            // Should think about optimization for O(n)
            val existedOperation =
                generationOperations.find { it.sourceImage == operation.sourceImage }
            val existedOperationIndex =
                existedOperation?.let {
                    generationOperations.indexOf(
                        existedOperation,
                    )
                }

            if (existedOperationIndex == null) {
                // Add as last
                generationOperations.add(operation)
            } else {
                // Just update existed
                generationOperations[existedOperationIndex] = operation
            }
        }

        is SKUGenerationOperation.SuccessOperation -> {
            generalScope.launch {
                // Try warm up first
                val isSuccessfullyUpload = addSuccessGenerations(operation)

                if (isSuccessfullyUpload) {
                    // Success case
                    // Let's save to history
                    saveGenerations(aiutaConfiguration = aiutaConfiguration, operation = operation)

                    // Set state as success
                    generationStatus.value = SKUGenerationUIStatus.SUCCESS
                    refreshOperation(operation)

                    // Navigate to results
                    navigateTo(NavigationScreen.GenerationResult)
                    deactivateGeneration()

                    // Only after navigation, let's change if need active image to backend
                    refreshActiveImage(
                        context = context,
                        generatedOperationFactory = generatedOperationFactory,
                        operation = operation,
                    )
                } else {
                    // Negative case
                    // Deactivate generation
                    deactivateGeneration()
                    generationStatus.value = SKUGenerationUIStatus.IDLE

                    showErrorState(
                        errorState =
                            TryOnToastErrorState(
                                aiutaConfiguration = aiutaConfiguration,
                                controller = this@solveOperationCollecting,
                                dialogController = dialogController,
                                context = context,
                                stringResources = stringResources,
                            ),
                    )
                }
            }
        }

        is SKUGenerationOperation.ErrorOperation -> {
            // Deactivate generation
            deactivateGeneration()
            generationStatus.value = SKUGenerationUIStatus.IDLE

            when {
                operation.exception?.isTryOnGenerationAbortedException() == true -> {
                    // Change to last success or empty operation
                    updateActiveOperationWithFirstOrSetEmpty()

                    // Need to change photo from user
                    dialogController.showDialog(
                        dialogState =
                            AiutaTryOnDialogState(
                                description = stringResources.dialogInvalidImageDescription,
                                confirmButton = stringResources.imageSelectorChangeButton,
                                onConfirm = dialogController::hideDialog,
                            ),
                    )
                }

                else -> {
                    showErrorState(
                        errorState =
                            TryOnToastErrorState(
                                aiutaConfiguration = aiutaConfiguration,
                                controller = this@solveOperationCollecting,
                                dialogController = dialogController,
                                context = context,
                                stringResources = stringResources,
                            ),
                    )
                }
            }

            refreshOperation(operation)
        }
    }
}

private suspend fun FashionTryOnController.refreshActiveImage(
    context: Context,
    generatedOperationFactory: GeneratedOperationFactory,
    operation: SKUGenerationOperation.SuccessOperation,
) {
    // Refresh only if active operation with local image
    if (lastSavedImages.value is LastSavedImages.UriSource) {
        val warmUpInteractor = WarmUpInteractor(context)

        val currentOperationId =
            generatedOperationFactory.getOperationId(
                imageId = operation.sourceImageId,
            )
        if (lastSavedOperation.value?.operationId != currentOperationId) {
            val images =
                listOf(
                    SourceImage(
                        imageId = operation.sourceImageId,
                        imageUrl = operation.sourceImage,
                    ),
                )

            // Warm up for smooth change
            warmUpInteractor.saveWarmUp(operation.sourceImage)

            // Change to new
            val newOperation =
                GeneratedOperationUIModel(
                    operationId = currentOperationId,
                    sourceImages = images,
                )
            updateActiveOperationOrSetEmpty(newOperation)
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

/**
 * @return true, if successfully load images and send analytic
 */
private suspend fun FashionTryOnController.addSuccessGenerations(
    newOperation: SKUGenerationOperation.SuccessOperation,
): Boolean {
    return try {
        val result =
            measureTimedValue {
                sessionGenerationInteractor.addGenerations(newOperation.generatedImages)
            }

        sendSuccessTryOnEvent(
            metadata = newOperation.metadata,
            downloadDuration = result.duration,
        )

        true
    } catch (e: Exception) {
        sendErrorDownloadResultEvent()
        false
    }
}

private suspend fun FashionTryOnController.saveGenerations(
    aiutaConfiguration: AiutaTryOnConfiguration,
    operation: SKUGenerationOperation.SuccessOperation,
) {
    // Save generations for history, if operation is success and history available
    if (aiutaConfiguration.toggles.isHistoryAvailable) {
        generatedImageInteractor.insertAll(
            generatedSkuId = activeSKUItem.value.skuId,
            images = operation.generatedImages,
        )
    }
}

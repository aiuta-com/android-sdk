package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.utils

import android.net.Uri
import com.aiuta.fashionsdk.analytic.model.StartUITryOn
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.operations.GeneratedOperationFactory
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUGenerationOperation
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.domain.models.toOperation
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.activateGeneration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.deactivateGeneration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.showErrorState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.analytic.sendStartUITryOnEvent
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationContainer
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus
import java.util.concurrent.atomic.AtomicInteger
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal fun FashionTryOnController.startGeneration(origin: StartUITryOn.Origin) {
    generationScope.launch {
        sendStartUITryOnEvent(origin)

        activateGeneration()

        val imageUris: List<Uri> = lastSavedPhotoUris.value.map { Uri.parse(it) }
        val errorCount = AtomicInteger()

        val generatedOperationFactory = GeneratedOperationFactory(generatedOperationInteractor)

        val generationFlows =
            imageUris.map { uri ->
                aiutaTryOn()
                    .startSKUGeneration(
                        container =
                            SKUGenerationContainer(
                                fileUri = uri,
                                skuId = activeSKUItem.value.skuId,
                                skuCatalogName = activeSKUItem.value.catalogName,
                            ),
                    )
                    .onEach { status ->
                        if (status is SKUGenerationStatus.LoadingGenerationStatus.UploadedSourceImage) {
                            generatedOperationInteractor.createImage(
                                imageUrl = status.sourceImageUrl,
                                operationId = generatedOperationFactory.getOperationId(),
                            )
                        }
                    }
                    .onEach { status ->
                        // Save generations for history, if operation is success
                        if (status is SKUGenerationStatus.SuccessGenerationStatus) {
                            generatedImageInteractor.insertAll(status.imageUrls)
                        }
                    }
                    .map { status -> status.toOperation(sourceUri = uri) }
                    .catch {
                        if (errorCount.incrementAndGet() == imageUris.size) {
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

private fun FashionTryOnController.solveOperationCollecting(operation: SKUGenerationOperation) {
    when (operation) {
        is SKUGenerationOperation.LoadingOperation -> {
            if (generationStatus.value != SKUGenerationUIStatus.SUCCESS) {
                generationStatus.value = SKUGenerationUIStatus.LOADING
            }

            // Check is this operation already exist
            // Should think about optimization for O(n)
            val existedOperation =
                generationOperations.find { it.sourceImageUri == operation.sourceImageUri }
            if (existedOperation == null) {
                generationOperations.add(operation)
            }
        }

        is SKUGenerationOperation.SuccessOperation -> {
            generationStatus.value = SKUGenerationUIStatus.SUCCESS
            refreshOperation(operation)
        }

        is SKUGenerationOperation.ErrorOperation -> {
            showErrorState()
            refreshOperation(operation)
        }
    }
}

private fun FashionTryOnController.refreshOperation(newOperation: SKUGenerationOperation) {
    generationOperations.replaceAll {
        if (it.sourceImageUri == newOperation.sourceImageUri) {
            newOperation
        } else {
            it
        }
    }
}

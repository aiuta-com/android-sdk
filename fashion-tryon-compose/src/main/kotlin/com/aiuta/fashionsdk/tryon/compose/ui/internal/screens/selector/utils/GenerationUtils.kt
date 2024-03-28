package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.utils

import android.net.Uri
import android.util.Log
import com.aiuta.fashionsdk.analytic.model.StartUITryOn
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUGenerationOperation
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.domain.models.toOperation
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.activateGeneration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.showErrorState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.analytic.sendStartUITryOnEvent
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationContainer
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal fun FashionTryOnController.startGeneration(origin: StartUITryOn.Origin) {
    scope.launch {
        sendStartUITryOnEvent(origin)

        activateGeneration()

        val imageUris: List<Uri> = lastSavedPhotoUris.value.map { Uri.parse(it) }

        // TODO Think about cancellation
        // TODO Create locally generation operation

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
                    .onEach {
                        // TODO If uploaded state - save to storage of operation
                        // insertOrUpdate
                    }
                    .onEach {
                        // Save generations for history, if operation is success
                        if (it is SKUGenerationStatus.SuccessGenerationStatus) {
                            generatedImageInteractor.insertAll(it.imageUrls)
                        }
                    }
                    .map { status -> status.toOperation(sourceUri = uri) }
            }

        generationFlows
            .merge()
            .catch { showErrorState() }
            .cancellable()
            .collect { operation -> solveOperationCollecting(operation) }
    }
}

private fun FashionTryOnController.solveOperationCollecting(operation: SKUGenerationOperation) {
    // TODO Delete
    Log.d("TAG_CHECK", "new operation - $operation")

    when (operation) {
        is SKUGenerationOperation.LoadingOperation -> {
            if (generationStatus.value != SKUGenerationUIStatus.SUCCESS) {
                generationStatus.value = SKUGenerationUIStatus.LOADING
            }

            // Check is this operation already exist
            // Should think about optimization for O(n)
            val existedOperation = generationOperations.find { it.sourceImageUri == operation.sourceImageUri }
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

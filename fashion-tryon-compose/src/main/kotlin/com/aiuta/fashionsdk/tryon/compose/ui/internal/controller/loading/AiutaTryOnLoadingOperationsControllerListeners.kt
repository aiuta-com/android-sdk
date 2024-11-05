package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.loading

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.aiuta.fashionsdk.tryon.compose.domain.models.dataprovider.toGeneratedImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.dataprovider.toGeneratedOperation
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.DeleteGeneratedImagesToastErrorState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.DeleteUploadedImagesToastErrorState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.showErrorState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.updateActiveOperationOrSetEmpty
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

// Generated images
@Composable
internal fun AiutaTryOnLoadingActionsController.deletingGeneratedImagesListener(
    controller: FashionTryOnController,
) {
    updateDeletingGeneratedImagesListener()
    showErrorDeletingGeneratedImagesListener(controller)
}

@Composable
private fun AiutaTryOnLoadingActionsController.updateDeletingGeneratedImagesListener() {
    val aiutaConfiguration = LocalAiutaConfiguration.current
    val dataProvider = aiutaConfiguration.dataProvider

    // Observe external changes of generated images and delete
    dataProvider?.let {
        LaunchedEffect(Unit) {
            dataProvider
                .generatedImagesFlow
                .map { images ->
                    // Make as list to compensate forEach with inner contains
                    images.map { image -> image.toGeneratedImage() }.toSet()
                }
                .onEach { images ->
                    // Get current loadings & retries
                    val loadingActiveGenerations = loadingGenerationsHolder.getList()
                    val retryActiveGenerations = retryGenerationsHolder.getList()

                    // Clean retries, because they can executed a little bit later
                    retryActiveGenerations.forEach { activeGeneration ->
                        if (!images.contains(activeGeneration)) {
                            retryGenerationsHolder.remove(activeGeneration)
                        }
                    }

                    // Clean loadings
                    loadingActiveGenerations.forEach { activeGeneration ->
                        if (!images.contains(activeGeneration)) {
                            loadingGenerationsHolder.remove(activeGeneration)
                        }
                    }
                }
                .launchIn(generalScope)
        }
    }
}

@Composable
private fun AiutaTryOnLoadingActionsController.showErrorDeletingGeneratedImagesListener(
    controller: FashionTryOnController,
) {
    val aiutaConfiguration = LocalAiutaConfiguration.current
    val dataProvider = aiutaConfiguration.dataProvider

    dataProvider?.let {
        LaunchedEffect(Unit) {
            dataProvider
                .isErrorDeletingGeneratedImagesFlow
                .onEach { isHostErrorDeletingGenerated ->
                    if (isHostErrorDeletingGenerated) {
                        // Move from loading to retry
                        val retryGenerations = loadingGenerationsHolder.getList()
                        retryGenerationsHolder.putAll(retryGenerations)

                        // Clean loading
                        loadingGenerationsHolder.removeAll()

                        controller.showErrorState(
                            errorState =
                                DeleteGeneratedImagesToastErrorState(
                                    controller = controller,
                                    loadingActionsController = this@showErrorDeletingGeneratedImagesListener,
                                ),
                        )
                    }
                }
                .launchIn(generalScope)
        }
    }
}

// Uploaded images
@Composable
internal fun AiutaTryOnLoadingActionsController.deletingUploadedImagesListener(
    controller: FashionTryOnController,
) {
    updateDeletingUploadedImagesListener(controller)
    showErrorDeletingUploadedImagesListener(controller)
}

@Composable
private fun AiutaTryOnLoadingActionsController.updateDeletingUploadedImagesListener(
    controller: FashionTryOnController,
) {
    val aiutaConfiguration = LocalAiutaConfiguration.current
    val dataProvider = aiutaConfiguration.dataProvider

    // Observe external changes of generated images and delete
    dataProvider?.let {
        LaunchedEffect(Unit) {
            dataProvider
                .uploadedImagesFlow
                .map { operations ->
                    // Make as list to compensate forEach with inner contains
                    operations.map { operation -> operation.toGeneratedOperation() }.toSet()
                }
                .onEach { operations ->
                    // Get current loadings & retries
                    val loadingActiveUploads = loadingUploadsHolder.getList()
                    val retryActiveUploads = retryUploadsHolder.getList()

                    // Clean retries, because they can executed a little bit later
                    retryActiveUploads.forEach { activeUpload ->
                        if (!operations.contains(activeUpload)) {
                            retryUploadsHolder.remove(activeUpload)
                        }
                    }

                    // Delete active loading uploads
                    loadingActiveUploads.forEach { activeUpload ->
                        if (!operations.contains(activeUpload)) {
                            loadingUploadsHolder.remove(activeUpload)
                        }
                    }

                    // Update active operation
                    controller.updateActiveOperationOrSetEmpty(operations.firstOrNull())
                }
                .launchIn(generalScope)
        }
    }
}

@Composable
private fun AiutaTryOnLoadingActionsController.showErrorDeletingUploadedImagesListener(
    controller: FashionTryOnController,
) {
    val aiutaConfiguration = LocalAiutaConfiguration.current
    val dataProvider = aiutaConfiguration.dataProvider

    dataProvider?.let {
        LaunchedEffect(Unit) {
            dataProvider
                .isErrorDeletingUploadedImagesFlow
                .onEach { isHostErrorDeletingUploaded ->
                    if (isHostErrorDeletingUploaded) {
                        // Move from loading to retry
                        val retryOperations = loadingUploadsHolder.getList()
                        retryUploadsHolder.putAll(retryOperations)

                        // Clean loading
                        loadingUploadsHolder.removeAll()

                        controller.showErrorState(
                            errorState =
                                DeleteUploadedImagesToastErrorState(
                                    controller = controller,
                                    loadingActionsController = this@showErrorDeletingUploadedImagesListener,
                                ),
                        )
                    }
                }
                .launchIn(generalScope)
        }
    }
}

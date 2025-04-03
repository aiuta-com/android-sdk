package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.utils

import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.images.cleanLoadingGenerations
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.DeleteGeneratedImagesToastErrorState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.deactivateSelectMode
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.loading.AiutaTryOnLoadingActionsController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.showErrorState
import kotlinx.coroutines.launch

internal fun FashionTryOnController.deleteGeneratedImages(
    loadingActionsController: AiutaTryOnLoadingActionsController,
) {
    generalScope.launch {
        try {
            val images = selectorHolder.getList()

            // After getting list, let's deactivate select changePhotoButtonStyle
            deactivateSelectMode()

            // Show as loading
            loadingActionsController.loadingGenerationsHolder.putAll(images)

            // Delete in db
            generatedImageInteractor.remove(images)
            // Clean, if it local changePhotoButtonStyle
            generatedImageInteractor.cleanLoadingGenerations(
                cleanAction = {
                    loadingActionsController.loadingGenerationsHolder.remove(images)
                },
            )

            // Also delete in session
            sessionGenerationInteractor.deleteGenerations(images)
        } catch (e: Exception) {
            showErrorState(
                errorState =
                DeleteGeneratedImagesToastErrorState(
                    controller = this@deleteGeneratedImages,
                    loadingActionsController = loadingActionsController,
                ),
            )
        }
    }
}

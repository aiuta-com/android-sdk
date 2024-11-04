package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.utils

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

            // After getting list, let's deactivate select mode
            deactivateSelectMode()

            // Show as loading
            loadingActionsController.loadingGenerationsHolder.putAll(images)

            // Delete in db
            generatedImageInteractor.remove(
                generatedImages = images,
                // Will execute only in local mode
                afterDeletionAction = loadingActionsController.loadingGenerationsHolder::removeAll,
            )

            // Also delete in session
            sessionGenerationInteractor.deleteGenerations(images.map { it.imageUrl })
        } catch (e: Exception) {
            showErrorState()
        }
    }
}

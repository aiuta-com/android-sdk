package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.utils

import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.deactivateSelectMode
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.showErrorState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal fun FashionTryOnController.deleteGeneratedImages(scope: CoroutineScope) {
    scope.launch {
        try {
            val images = selectorHolder.getList()

            // After getting list, let's deactivate select mode
            deactivateSelectMode()

            // Show as loading
            loadingGenerationsHolder.putAll(images)

            // Delete in db
            generatedImageInteractor.remove(
                generatedImages = images,
                afterDeletionAction = loadingGenerationsHolder::removeAll, // Will execute only in local mode
            )

            // Also delete in session
            sessionGenerationInteractor.deleteGenerations(images.map { it.imageUrl })
        } catch (e: Exception) {
            showErrorState()
        }
    }
}

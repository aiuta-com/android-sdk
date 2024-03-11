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

            generatedImageInteractor.remove(images)

            deactivateSelectMode()
        } catch (e: Exception) {
            showErrorState()
        }
    }
}

package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.aiuta.fashionsdk.analytic.model.ViewGeneratedImage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic.sendViewGeneratedImageEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic.sendViewMoreToTryOnEvent

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun GenerationResultControllerListener(
    generationResultController: GenerationResultController,
) {
    val controller = LocalController.current

    // Listen for scroll
    LaunchedEffect(generationResultController.generationPagerState.settledPage) {
        controller.sendViewGeneratedImageEvent(
            newIndex = generationResultController.generationPagerState.settledPage,
            type = ViewGeneratedImage.NavigationType.SWIPE,
        )
    }

    LaunchedEffect(generationResultController.verticalSwipeState.currentValue) {
        val verticalSwipeState = generationResultController.verticalSwipeState.currentValue
        if (verticalSwipeState == GenerateResultState.SHOW_GENERATE_MORE) {
            controller.sendViewMoreToTryOnEvent()
        }
    }
}

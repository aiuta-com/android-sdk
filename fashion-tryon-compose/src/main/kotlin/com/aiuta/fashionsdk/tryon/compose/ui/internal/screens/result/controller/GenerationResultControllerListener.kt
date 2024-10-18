package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import com.aiuta.fashionsdk.internal.analytic.model.ViewGeneratedImage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateBack
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic.sendViewGeneratedImageEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic.sendViewMoreToTryOnEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.utils.startGeneration

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
        if (verticalSwipeState == GenerateResultStatus.MORE_EXPANDED) {
            controller.sendViewMoreToTryOnEvent()
        }
    }
}

@Composable
internal fun GenerateMoreListener(isActive: State<Boolean>) {
    val controller = LocalController.current
    val aiutaConfiguration = LocalAiutaConfiguration.current

    // Wait for bottom sheet changes and start generation
    LaunchedEffect(controller.lastSavedImages.value) {
        if (isActive.value) {
            controller.startGeneration(
                aiutaConfiguration = aiutaConfiguration,
            )
            controller.navigateBack()
        }
    }
}

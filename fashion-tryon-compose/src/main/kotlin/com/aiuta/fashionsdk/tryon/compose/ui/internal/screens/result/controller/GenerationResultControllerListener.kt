package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.aiuta.fashionsdk.internal.analytic.model.ViewGeneratedImage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDialogController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateBack
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic.sendViewGeneratedImageEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic.sendViewMoreToTryOnEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.utils.startGeneration

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

    LaunchedEffect(
        generationResultController.bottomSheetScaffoldState.bottomSheetState.isExpanded,
    ) {
        val bottomSheetState = generationResultController.bottomSheetScaffoldState.bottomSheetState
        if (bottomSheetState.isExpanded) {
            controller.sendViewMoreToTryOnEvent()
        }
    }
}

@Composable
internal fun GenerateMoreListener() {
    val context = LocalContext.current
    val controller = LocalController.current
    val aiutaConfiguration = LocalAiutaConfiguration.current
    val dialogController = LocalAiutaTryOnDialogController.current
    val stringResources = LocalAiutaTryOnStringResources.current

    // Wait for bottom sheet changes and start generation
    LaunchedEffect(controller.isAutoTryOnEnabled.value) {
        if (controller.isAutoTryOnEnabled.value) {
            controller.startGeneration(
                aiutaConfiguration = aiutaConfiguration,
                context = context,
                dialogController = dialogController,
                stringResources = stringResources,
            )
            controller.navigateBack()
        }
    }
}

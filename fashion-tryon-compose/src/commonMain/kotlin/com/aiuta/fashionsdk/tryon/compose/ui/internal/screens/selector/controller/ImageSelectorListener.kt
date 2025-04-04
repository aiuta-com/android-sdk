package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import coil3.compose.LocalPlatformContext
import com.aiuta.fashionsdk.internal.analytic.model.StartTryOnEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDialogController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.disableAutoTryOn
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.utils.startGeneration

@Composable
internal fun ImageSelectorAutoTryOnListener() {
    val coilContext = LocalPlatformContext.current
    val controller = LocalController.current
    val aiutaConfiguration = LocalAiutaConfiguration.current
    val dialogController = LocalAiutaTryOnDialogController.current
    val stringResources = LocalAiutaTryOnStringResources.current

    LaunchedEffect(controller.isAutoTryOnEnabled.value) {
        if (controller.isAutoTryOnEnabled.value) {
            controller.disableAutoTryOn()
            controller.startGeneration(
                aiutaConfiguration = aiutaConfiguration,
                coilContext = coilContext,
                dialogController = dialogController,
                stringResources = stringResources,
                origin = StartTryOnEvent.TryOnOrigin.SELECTED_PHOTO,
            )
        }
    }
}

package com.aiuta.fashionsdk.tryon.compose.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.compose.tokens.AiutaTheme
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnListeners
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendConfigureEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendStartSessionEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.deactivateSelectMode
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateBack
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationContainer
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationInitialisation
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.ZoomedImageScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.ZoomImageState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.closeZoomImageScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.disableZoomState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.isTransitionActive
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.isZoomEnable
import com.aiuta.fashionsdk.tryon.core.AiutaTryOn

/**
 * Entry point for fashion try on flow
 *
 * @see AiutaTryOn
 * @see AiutaTryOnListeners
 * @see AiutaTheme
 * @see SKUItem
 */
@Composable
public fun AiutaTryOnFlow(
    modifier: Modifier = Modifier,
    aiuta: () -> Aiuta,
    aiutaTryOn: () -> AiutaTryOn,
    aiutaTryOnListeners: () -> AiutaTryOnListeners,
    aiutaTryOnConfiguration: () -> AiutaTryOnConfiguration,
    aiutaTheme: AiutaTheme,
    skuForGeneration: () -> SKUItem,
) {
    val scope = rememberCoroutineScope()

    NavigationInitialisation(
        modifier = modifier,
        aiuta = aiuta,
        aiutaTryOn = aiutaTryOn,
        aiutaTryOnListeners = aiutaTryOnListeners,
        aiutaTryOnConfiguration = aiutaTryOnConfiguration,
        aiutaTheme = aiutaTheme,
        skuForGeneration = skuForGeneration,
    ) {
        val controller = LocalController.current

        sendStartSessionEvent()
        sendConfigureEvent(aiutaTheme)

        NavigationContainer(
            modifier = modifier,
        )

        // Move screen here, because full view should be on the top of navigation
        with(controller) {
            if (zoomImageController.zoomState.value == ZoomImageState.ENABLE) {
                ZoomedImageScreen(
                    modifier = modifier,
                    screenState = zoomImageController,
                    onTransitionFinished = {
                        if (!zoomImageController.isTransitionActive()) {
                            zoomImageController.disableZoomState()
                        }
                    },
                )
            }
        }

        BackHandler {
            when {
                controller.zoomImageController.isZoomEnable() -> {
                    controller.zoomImageController.closeZoomImageScreen(scope)
                }

                controller.bottomSheetNavigator.sheetState.isVisible -> {
                    controller.bottomSheetNavigator.hide()
                }

                controller.currentScreen.value == NavigationScreen.HISTORY -> {
                    // Use custom, because we need deactivate select mode first
                    controller.deactivateSelectMode()
                    controller.navigateBack()
                }

                else -> controller.navigateBack()
            }
        }
    }
}

package com.aiuta.fashionsdk.tryon.compose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.aiuta.fashionsdk.compose.tokens.AiutaTheme
import com.aiuta.fashionsdk.internal.analytic.model.SessionEvent
import com.aiuta.fashionsdk.tryon.compose.configuration.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.configuration.listeners.AiutaTryOnListeners
import com.aiuta.fashionsdk.tryon.compose.configuration.models.product.SKUItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendSessionEvent
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
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.backhandler.BackHandler

/**
 * Entry point for fashion try on flow
 *
 * @see AiutaTryOnConfiguration
 * @see AiutaTheme
 * @see SKUItem
 */
@Composable
public fun AiutaTryOnFlow(
    modifier: Modifier = Modifier,
    aiutaTryOnConfiguration: AiutaTryOnConfiguration,
    aiutaTryOnListeners: AiutaTryOnListeners,
    aiutaTheme: AiutaTheme,
    skuForGeneration: SKUItem,
) {
    NavigationInitialisation(
        modifier = modifier,
        aiutaTryOnConfiguration = aiutaTryOnConfiguration,
        aiutaTryOnListeners = aiutaTryOnListeners,
        aiutaTheme = aiutaTheme,
        skuForGeneration = skuForGeneration,
    ) {
        sendSessionEvent(SessionEvent.FlowType.TRY_ON)

        val controller = LocalController.current
        val scope = rememberCoroutineScope()

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

                controller.currentScreen.value == NavigationScreen.History -> {
                    // Use custom, because we need deactivate select mode first
                    controller.deactivateSelectMode()
                    controller.navigateBack()
                }

                else -> controller.navigateBack()
            }
        }
    }
}

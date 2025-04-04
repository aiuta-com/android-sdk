package com.aiuta.fashionsdk.tryon.compose.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.compose.tokens.AiutaTheme
import com.aiuta.fashionsdk.internal.analytic.model.SessionEvent
import com.aiuta.fashionsdk.tryon.compose.domain.models.DefaultSKUItem
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.listeners.AiutaTryOnListeners
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendSessionEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.deactivateSelectMode
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateBack
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateTo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationInitialisation
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.HistoryScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.ZoomedImageScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.ZoomImageState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.closeZoomImageScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.disableZoomState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.isTransitionActive
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.isZoomEnable
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.backhandler.BackHandler
import com.aiuta.fashionsdk.tryon.core.AiutaTryOn

/**
 * Entry point for history flow
 *
 * @see Aiuta
 * @see AiutaTryOn
 * @see AiutaTryOnListeners
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
public fun HistoryFlow(
    modifier: Modifier = Modifier,
    aiutaTryOnConfiguration: AiutaTryOnConfiguration,
    aiutaTryOnListeners: AiutaTryOnListeners,
    aiutaTheme: AiutaTheme,
) {
    NavigationInitialisation(
        modifier = modifier,
        aiutaTryOnConfiguration = aiutaTryOnConfiguration,
        aiutaTheme = aiutaTheme,
        aiutaTryOnListeners = aiutaTryOnListeners,
        skuForGeneration = DefaultSKUItem,
    ) {
        sendSessionEvent(SessionEvent.FlowType.HISTORY)

        val scope = rememberCoroutineScope()
        val controller = LocalController.current

        LaunchedEffect(Unit) {
            controller.navigateTo(NavigationScreen.History)
        }

        HistoryScreen(modifier = Modifier.fillMaxSize())

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

package com.aiuta.fashionsdk.tryon.compose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import com.aiuta.fashionsdk.configuration.AiutaConfiguration
import com.aiuta.fashionsdk.configuration.features.models.product.ProductItem
import com.aiuta.fashionsdk.internal.analytic.model.SessionEvent
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

/**
 * Entry point for fashion try on flow
 *
 * @see AiutaConfiguration
 * @see ProductItem
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
public fun AiutaTryOnFlow(
    modifier: Modifier = Modifier,
    aiutaConfiguration: AiutaConfiguration,
    productForGeneration: ProductItem,
) {
    NavigationInitialisation(
        modifier = modifier,
        aiutaConfiguration = aiutaConfiguration,
        productItem = productForGeneration,
    ) {
        sendSessionEvent(SessionEvent.FlowType.TRY_ON)

        val controller = LocalController.current
        val scope = rememberCoroutineScope()

        NavigationContainer(modifier = modifier)

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
                    // Use custom, because we need deactivate select changePhotoButtonStyle first
                    controller.deactivateSelectMode()
                    controller.navigateBack()
                }

                else -> controller.navigateBack()
            }
        }
    }
}

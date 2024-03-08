package com.aiuta.fashionsdk.tryon.compose.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnTheme
import com.aiuta.fashionsdk.tryon.compose.domain.models.FashionTryOnListeners
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.domain.models.toTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.deactivateSelectMode
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateBack
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.rememberFashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationContainer
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.ZoomedImageScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.ZoomImageState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.closeZoomImageScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.disableZoomState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.isTransitionActive
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.isZoomEnable
import com.aiuta.fashionsdk.tryon.core.FashionTryOn

/**
 * Entry point for fashion try on flow
 *
 * @see FashionTryOn
 * @see FashionTryOnListeners
 * @see AiutaTryOnTheme
 * @see SKUItem
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
public fun FashionTryOnFlow(
    modifier: Modifier = Modifier,
    fashionTryOn: () -> FashionTryOn,
    fashionTryOnListeners: () -> FashionTryOnListeners,
    aiutaTryOnTheme: (() -> AiutaTryOnTheme)? = null,
    skuForGeneration: () -> SKUItem,
) {
    val scope = rememberCoroutineScope()

    BoxWithConstraints(
        modifier = modifier,
    ) {
        val controller =
            rememberFashionTryOnController(
                fashionTryOn = fashionTryOn,
                fashionTryOnListeners = fashionTryOnListeners,
                skuForGeneration = skuForGeneration,
            )
        val theme = remember { aiutaTryOnTheme?.invoke().toTheme() }

        CompositionLocalProvider(
            LocalController provides controller,
            LocalTheme provides theme,
        ) {
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
}

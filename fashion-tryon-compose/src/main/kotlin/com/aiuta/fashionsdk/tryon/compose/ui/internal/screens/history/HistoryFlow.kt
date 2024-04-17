package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnListeners
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnTheme
import com.aiuta.fashionsdk.tryon.compose.domain.models.defaultSKUItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.deactivateSelectMode
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateBack
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateTo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationInitialisation
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.NavigationAppBar
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.ZoomedImageScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.ZoomImageState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.closeZoomImageScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.disableZoomState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.isTransitionActive
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.isZoomEnable
import com.aiuta.fashionsdk.tryon.core.AiutaTryOn

/**
 * Entry point for history flow
 *
 * @see Aiuta
 * @see AiutaTryOn
 * @see AiutaTryOnListeners
 * @see AiutaTryOnTheme
 */
@Composable
public fun HistoryFlow(
    modifier: Modifier = Modifier,
    aiuta: () -> Aiuta,
    aiutaTryOn: () -> AiutaTryOn,
    aiutaTryOnListeners: () -> AiutaTryOnListeners,
    theme: (() -> AiutaTryOnTheme)? = null,
) {
    NavigationInitialisation(
        modifier = modifier,
        aiuta = aiuta,
        aiutaTryOn = aiutaTryOn,
        aiutaTryOnListeners = aiutaTryOnListeners,
        theme = theme,
        skuForGeneration = { defaultSKUItem },
    ) {
        val scope = rememberCoroutineScope()
        val controller = LocalController.current

        LaunchedEffect(Unit) {
            controller.navigateTo(NavigationScreen.HISTORY)
        }

        HistoryScreenContent(
            modifier = Modifier.fillMaxSize(),
        )

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

@Composable
private fun HistoryScreenContent(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val theme = LocalTheme.current
    val sharedModifier = Modifier.zIndex(controller.zIndexInterface).fillMaxWidth()

    Column(
        modifier = modifier.background(theme.colors.background),
    ) {
        NavigationAppBar(
            modifier =
                sharedModifier.background(
                    theme.colors.background,
                ).windowInsetsPadding(WindowInsets.statusBars),
            navigateBack = controller::navigateBack,
            navigateToHistory = {
                controller.navigateTo(NavigationScreen.HISTORY)
            },
        )

        HistoryScreenInternal(
            modifier = Modifier.fillMaxSize(),
        )
    }
}

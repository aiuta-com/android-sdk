package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateTo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.transition.leftToRightTransition
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.transition.rightToLeftTransition
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.HistoryScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.OnboardingScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.GenerationResultScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.ImageSelectorScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.spalsh.SplashScreen

@Composable
internal fun NavigationContent(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val sharedModifier = Modifier.fillMaxSize()

    val transition =
        updateTransition(
            targetState = controller.currentScreen.value,
            label = "navigation transition",
        )

    transition.AnimatedContent(
        modifier = modifier,
        transitionSpec = {
            if (initialState.ordinal < targetState.ordinal) {
                rightToLeftTransition
            } else {
                leftToRightTransition
            }
        },
    ) { targetScreen ->
        when (targetScreen) {
            NavigationScreen.SPLASH -> {
                SplashScreen(
                    modifier = sharedModifier,
                    navigateTo = controller::navigateTo,
                )
            }

            NavigationScreen.ONBOARDING -> {
                OnboardingScreen(
                    modifier = sharedModifier,
                )
            }

            NavigationScreen.HISTORY -> {
                HistoryScreen(
                    modifier = sharedModifier,
                )
            }

            NavigationScreen.IMAGE_SELECTOR -> {
                ImageSelectorScreen(
                    modifier = sharedModifier,
                )
            }

            NavigationScreen.GENERATION_RESULT -> {
                GenerationResultScreen(
                    modifier = sharedModifier,
                )
            }
        }
    }
}

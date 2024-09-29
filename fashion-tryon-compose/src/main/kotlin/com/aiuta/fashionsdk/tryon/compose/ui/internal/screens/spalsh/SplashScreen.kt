package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.spalsh

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.toLastSavedImages
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDataController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.data.preloadConfig
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.validateControllerCache
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
internal fun SplashScreen(
    modifier: Modifier = Modifier,
    navigateTo: (NavigationScreen) -> Unit,
) {
    val theme = LocalTheme.current
    val controller = LocalController.current
    val configuration = LocalAiutaConfiguration.current
    val dataController = LocalAiutaTryOnDataController.current

    LaunchedEffect(Unit) {
        // Try to preload config
        launch { dataController.preloadConfig() }

        // Validate controller
        validateControllerCache(aiuta = controller.aiuta)

        // Check operation history
        val countGeneratedOperation =
            controller.generatedOperationInteractor
                .countGeneratedOperation()
                .first()

        if (countGeneratedOperation > 0) {
            val lastOperation = controller.generatedOperationInteractor.getLastGeneratedOperation()
            controller.lastSavedImages.value = lastOperation.toLastSavedImages()
        }

        // Solve should show onboarding or not
        val shouldShowOnboarding = controller.onboardingInteractor.shouldShowOnboarding()

        if (shouldShowOnboarding) {
            val firstOnboardingScreen =
                if (configuration.isPreOnboardingAvailable) {
                    NavigationScreen.PREONBOARDING
                } else {
                    NavigationScreen.ONBOARDING
                }

            navigateTo(firstOnboardingScreen)
        } else {
            navigateTo(NavigationScreen.IMAGE_SELECTOR)
        }
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(0.4f),
            painter = painterResource(theme.navBarTheme.navLogo),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
    }
}

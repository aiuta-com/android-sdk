package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.spalsh

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.aiuta.fashionsdk.configuration.features.consent.AiutaConsentStandaloneOnboardingPageFeature
import com.aiuta.fashionsdk.configuration.features.onboarding.AiutaOnboardingFeature
import com.aiuta.fashionsdk.configuration.features.welcome.AiutaWelcomeScreenFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaFeatures
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDataController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.data.preloadConfig
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.updateActiveOperationWithFirstOrSetEmpty
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.validateControllerCache
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.provideFeature
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
internal fun SplashScreen(
    modifier: Modifier = Modifier,
    navigateTo: (NavigationScreen) -> Unit,
) {
    val controller = LocalController.current
    val features = LocalAiutaFeatures.current
    val dataController = LocalAiutaTryOnDataController.current

    val onboardingFeature = provideFeature<AiutaOnboardingFeature>()
    val consentStandaloneFeature = provideFeature<AiutaConsentStandaloneOnboardingPageFeature>()

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
            controller.updateActiveOperationWithFirstOrSetEmpty()
        }

        // Solve should show onboarding or not
        val isHostOnboardingPassed = onboardingFeature?.dataProvider?.isOnboardingCompleted?.value
        val isOnboardingPassed = isHostOnboardingPassed ?: controller.onboardingInteractor.isOnboardingPassed()

        val shouldShowOnboarding = when {
            // Onboarding not provided
            !features.isFeatureInitialize<AiutaOnboardingFeature>() -> false
            // SDK didn't show onboarding
            !isOnboardingPassed -> true
            // If consent is part of onboarding and some mandatory consents not seen
            consentStandaloneFeature != null &&
                controller.consentInteractor.shouldShowConsent(
                    consentStandaloneFeature,
                ) -> true
            else -> false
        }

        if (shouldShowOnboarding) {
            val firstOnboardingScreen =
                when {
                    features.isFeatureInitialize<AiutaWelcomeScreenFeature>() -> NavigationScreen.Preonboarding
                    features.isFeatureInitialize<AiutaOnboardingFeature>() -> NavigationScreen.Onboarding
                    else -> NavigationScreen.ImageSelector
                }

            navigateTo(firstOnboardingScreen)
        } else {
            navigateTo(NavigationScreen.ImageSelector)
        }
    }

    Box(modifier = modifier)
}

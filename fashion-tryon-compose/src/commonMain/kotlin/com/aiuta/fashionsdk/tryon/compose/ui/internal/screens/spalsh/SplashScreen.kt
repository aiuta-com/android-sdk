package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.spalsh

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.AiutaConsentStandaloneOnboardingPageFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.AiutaOnboardingFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.welcome.AiutaWelcomeScreenFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDataController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.data.preloadConfig
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.updateActiveOperationWithFirstOrSetEmpty
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.validateControllerCache
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.isFeatureInitialize
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.provideFeature
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
internal fun SplashScreen(
    modifier: Modifier = Modifier,
    navigateTo: (NavigationScreen) -> Unit,
) {
    val controller = LocalController.current
    val configuration = LocalAiutaConfiguration.current
    val dataController = LocalAiutaTryOnDataController.current

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
        val isUserConsentObtainedFlowRaw =
            consentStandaloneFeature?.dataProvider?.isUserConsentObtainedFlow?.value
        val shouldShowOnboardingFromHost =
            isUserConsentObtainedFlowRaw != null && isUserConsentObtainedFlowRaw == false
        val shouldShowOnboarding =
            controller.onboardingInteractor.shouldShowOnboarding() || shouldShowOnboardingFromHost

        if (shouldShowOnboarding) {
            val firstOnboardingScreen =
                when {
                    configuration.isFeatureInitialize<AiutaWelcomeScreenFeature>() -> NavigationScreen.Preonboarding
                    configuration.isFeatureInitialize<AiutaOnboardingFeature>() -> NavigationScreen.Onboarding
                    else -> NavigationScreen.ImageSelector
                }

            navigateTo(firstOnboardingScreen)
        } else {
            navigateTo(NavigationScreen.ImageSelector)
        }
    }

    Box(modifier = modifier)
}

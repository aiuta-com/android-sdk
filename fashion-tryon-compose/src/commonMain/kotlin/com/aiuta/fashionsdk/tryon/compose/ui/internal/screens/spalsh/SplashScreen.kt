package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.spalsh

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.aiuta.fashionsdk.configuration.features.features.consent.standalone.AiutaConsentStandaloneOnboardingPageFeature
import com.aiuta.fashionsdk.configuration.features.features.onboarding.AiutaOnboardingFeature
import com.aiuta.fashionsdk.configuration.features.features.welcome.AiutaWelcomeScreenFeature
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
        val isOnboardingPassed = controller.onboardingInteractor.isOnboardingPassed()

        val shouldShowOnboarding = when {
            // Onboarding not provided
            !configuration.isFeatureInitialize<AiutaOnboardingFeature>() -> false
            // SDK didn't show onboarding
            !isOnboardingPassed -> true

            consentStandaloneFeature != null -> {
                // This is standalone consent
                val localAllConsentIds = controller.onboardingInteractor.getConsentIds().sorted()
                val localObtainedConsentIds = controller.onboardingInteractor.getObtainedConsentIds().sorted()

                val hostAllConsentIds = consentStandaloneFeature.data.consents.map { it.id }
                val hostObtainedConsentIds = consentStandaloneFeature.dataProvider?.obtainedConsentsIds?.value?.sorted()
                val hostRequiredConsentsIds = consentStandaloneFeature.data.consents.mapNotNull { consent ->
                    if (consent.isRequired) {
                        consent.id
                    } else {
                        null
                    }
                }

                when {
                    // If host and SDK have not same obtained consents (with data provider)
                    hostObtainedConsentIds != null && hostObtainedConsentIds != localObtainedConsentIds -> true
                    // If host and SDK have not same required consents
                    !localObtainedConsentIds.containsAll(hostRequiredConsentsIds) -> true
                    // If host and SDK have not same list of all consents, but host give short list of already obtained consents -> let's don't show
                    hostAllConsentIds.size < localAllConsentIds.size && localObtainedConsentIds.containsAll(hostAllConsentIds) -> false
                    // If host and SDK have not same list of all consents
                    hostAllConsentIds != localAllConsentIds -> true
                    else -> false
                }
            }
            else -> false
        }

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

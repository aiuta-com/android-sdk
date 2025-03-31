package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.consent

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.AiutaConsentFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.checkFeatureAvailability

@Composable
@ReadOnlyComposable
internal fun consentFeature(): AiutaConsentFeature? {
    val aiutaConfiguration = LocalAiutaConfiguration.current
    return aiutaConfiguration.features.consent
}

@Composable
@ReadOnlyComposable
internal fun consentBuiltInFeature(): AiutaConsentFeature.BuiltInWithOnboarding? {
    return consentFeature() as? AiutaConsentFeature.BuiltInWithOnboarding
}

@Composable
@ReadOnlyComposable
internal fun consentStandaloneFeature(): AiutaConsentFeature.StandaloneOnboardingPage? {
    return consentFeature() as? AiutaConsentFeature.StandaloneOnboardingPage
}

@Composable
@ReadOnlyComposable
internal fun strictConsentStandaloneFeature(): AiutaConsentFeature.StandaloneOnboardingPage {
    return checkFeatureAvailability(
        name = "AiutaConsentFeature.StandaloneOnboardingPage",
        feature = consentStandaloneFeature(),
    )
}

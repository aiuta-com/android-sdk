package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.consent

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.AiutaConsentFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.builtin.AiutaConsentBuiltInWithOnboardingPage
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.AiutaConsentStandaloneOnboardingPage
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
internal fun consentBuiltInFeature(): AiutaConsentBuiltInWithOnboardingPage? {
    return consentFeature() as? AiutaConsentBuiltInWithOnboardingPage
}

@Composable
@ReadOnlyComposable
internal fun consentStandaloneFeature(): AiutaConsentStandaloneOnboardingPage? {
    return consentFeature() as? AiutaConsentStandaloneOnboardingPage
}

@Composable
@ReadOnlyComposable
internal fun strictConsentStandaloneFeature(): AiutaConsentStandaloneOnboardingPage {
    return checkFeatureAvailability(
        name = "AiutaConsentFeature.StandaloneOnboardingPage",
        feature = consentStandaloneFeature(),
    )
}

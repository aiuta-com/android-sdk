package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state

import com.aiuta.fashionsdk.configuration.features.consent.AiutaConsentStandaloneOnboardingPageFeature

internal class ConsentPage(
    consentStandaloneFeature: AiutaConsentStandaloneOnboardingPageFeature,
) : OnboardingState {
    override val pageTitle: String? = consentStandaloneFeature.strings.consentPageTitle
}

package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state

import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.AiutaConsentStandaloneOnboardingPage

internal class ConsentPage(
    consentStandaloneFeature: AiutaConsentStandaloneOnboardingPage,
) : OnboardingState {
    override val pageTitle: String? = consentStandaloneFeature.strings.consentPageTitle
}

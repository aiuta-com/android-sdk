package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state

import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.AiutaConsentFeature

internal class ConsentPage(
    consentStandaloneFeature: AiutaConsentFeature.StandaloneOnboardingPage,
) : OnboardingState {
    override val pageTitle: String? = consentStandaloneFeature.strings.consentPageTitle
}

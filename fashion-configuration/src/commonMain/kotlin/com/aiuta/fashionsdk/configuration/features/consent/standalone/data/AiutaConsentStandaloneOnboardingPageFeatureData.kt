package com.aiuta.fashionsdk.configuration.features.consent.standalone.data

import com.aiuta.fashionsdk.configuration.features.consent.models.AiutaConsent

public interface AiutaConsentStandaloneOnboardingPageFeatureData {
    public val consents: List<AiutaConsent>

    public class Default : AiutaConsentStandaloneOnboardingPageFeatureData {
        override val consents: List<AiutaConsent> = listOf(
            AiutaConsent(
                id = "aiuta_consent_1",
                consentHtml = "I agree to allow Aiuta to process my photo",
                isRequired = true,
            ),
        )
    }
}

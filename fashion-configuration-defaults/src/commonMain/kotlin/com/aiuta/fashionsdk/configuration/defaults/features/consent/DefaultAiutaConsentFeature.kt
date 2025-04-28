package com.aiuta.fashionsdk.configuration.defaults.features.consent

import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.features.consent.builtin.strings.AiutaConsentEmbeddedIntoOnboardingFeatureStrings
import com.aiuta.fashionsdk.configuration.features.consent.embeddedConsent

public fun AiutaFeatures.Builder.defaultConsent(
    termsOfServiceUrl: String,
): AiutaFeatures.Builder = embeddedConsent {
    strings = AiutaConsentEmbeddedIntoOnboardingFeatureStrings.Default(termsOfServiceUrl)
}

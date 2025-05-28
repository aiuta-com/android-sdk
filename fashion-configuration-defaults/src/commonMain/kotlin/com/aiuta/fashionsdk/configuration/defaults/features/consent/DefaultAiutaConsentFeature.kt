package com.aiuta.fashionsdk.configuration.defaults.features.consent

import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.features.consent.builtin.strings.AiutaConsentEmbeddedIntoOnboardingFeatureStrings
import com.aiuta.fashionsdk.configuration.features.consent.embeddedConsent

/**
 * Configures the default consent feature for the Aiuta SDK.
 *
 * This function sets up the consent feature with default strings and terms of service URL.
 *
 * @param termsOfServiceUrl The URL for the terms of service.
 * @return The updated [AiutaFeatures.Builder] instance.
 */
public fun AiutaFeatures.Builder.defaultConsent(
    termsOfServiceUrl: String,
): AiutaFeatures.Builder = embeddedConsent {
    strings = AiutaConsentEmbeddedIntoOnboardingFeatureStrings.Default(termsOfServiceUrl)
}

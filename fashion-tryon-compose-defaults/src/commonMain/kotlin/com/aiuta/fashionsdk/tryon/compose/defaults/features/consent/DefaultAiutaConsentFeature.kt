package com.aiuta.fashionsdk.tryon.compose.defaults.features.consent

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.strings.AiutaConsentStandaloneOnboardingPageFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standaloneConsent

public fun AiutaTryOnConfigurationFeatures.Builder.defaultConsent() {
    standaloneConsent {
        strings = AiutaConsentStandaloneOnboardingPageFeatureStrings.Default()
    }
}

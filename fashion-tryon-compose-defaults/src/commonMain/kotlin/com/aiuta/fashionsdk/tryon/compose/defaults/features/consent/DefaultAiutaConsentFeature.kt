package com.aiuta.fashionsdk.tryon.compose.defaults.features.consent

import com.aiuta.fashionsdk.configuration.features.features.AiutaTryOnConfigurationFeatures
import com.aiuta.fashionsdk.configuration.features.features.consent.standalone.data.AiutaConsentStandaloneOnboardingPageFeatureData
import com.aiuta.fashionsdk.configuration.features.features.consent.standalone.strings.AiutaConsentStandaloneOnboardingPageFeatureStrings
import com.aiuta.fashionsdk.configuration.features.features.consent.standaloneConsent

public fun AiutaTryOnConfigurationFeatures.Builder.defaultConsent() {
    standaloneConsent {
        strings = AiutaConsentStandaloneOnboardingPageFeatureStrings.Default()
        data = AiutaConsentStandaloneOnboardingPageFeatureData.Default()
    }
}

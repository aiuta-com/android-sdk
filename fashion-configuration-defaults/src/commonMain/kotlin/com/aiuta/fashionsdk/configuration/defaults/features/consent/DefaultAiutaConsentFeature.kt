package com.aiuta.fashionsdk.configuration.defaults.features.consent

import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.features.consent.standalone.data.AiutaConsentStandaloneOnboardingPageFeatureData
import com.aiuta.fashionsdk.configuration.features.consent.standalone.strings.AiutaConsentStandaloneOnboardingPageFeatureStrings
import com.aiuta.fashionsdk.configuration.features.consent.standaloneConsent

public fun AiutaFeatures.Builder.defaultConsent() {
    standaloneConsent {
        strings = AiutaConsentStandaloneOnboardingPageFeatureStrings.Default()
        data = AiutaConsentStandaloneOnboardingPageFeatureData.Default()
    }
}

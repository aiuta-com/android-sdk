package com.aiuta.fashionsdk.tryon.compose.defaults.features.consent

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.strings.AiutaConsentStandaloneOnboardingPageStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standaloneConsent

public fun AiutaTryOnFeatures.Builder.defaultConsent() {
    standaloneConsent {
        strings = AiutaConsentStandaloneOnboardingPageStrings.Default()
    }
}

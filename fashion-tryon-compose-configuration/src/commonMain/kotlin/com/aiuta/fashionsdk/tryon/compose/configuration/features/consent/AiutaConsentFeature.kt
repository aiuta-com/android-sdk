package com.aiuta.fashionsdk.tryon.compose.configuration.features.consent

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.builtin.AiutaConsentBuiltInWithOnboardingPage
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.AiutaConsentStandaloneOnboardingPage

public interface AiutaConsentFeature {
    public interface Builder {
        public fun build(): AiutaConsentFeature
    }
}

public inline fun AiutaTryOnFeatures.Builder.builtInConsent(
    block: AiutaConsentBuiltInWithOnboardingPage.Builder.() -> Unit,
) {
    consent = AiutaConsentBuiltInWithOnboardingPage.Builder().apply(block).build()
}

public inline fun AiutaTryOnFeatures.Builder.standaloneConsent(
    block: AiutaConsentStandaloneOnboardingPage.Builder.() -> Unit,
) {
    consent = AiutaConsentStandaloneOnboardingPage.Builder().apply(block).build()
}

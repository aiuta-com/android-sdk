package com.aiuta.fashionsdk.tryon.compose.configuration.features.consent

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.builtin.AiutaConsentBuiltInWithOnboardingPage
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.AiutaConsentStandaloneOnboardingPage

@Immutable
public interface AiutaConsentFeature : AiutaFeature

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

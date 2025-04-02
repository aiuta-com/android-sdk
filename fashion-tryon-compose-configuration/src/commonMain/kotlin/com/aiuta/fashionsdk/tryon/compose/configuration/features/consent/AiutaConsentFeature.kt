package com.aiuta.fashionsdk.tryon.compose.configuration.features.consent

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.builtin.AiutaConsentBuiltInWithOnboardingPageFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.AiutaConsentStandaloneOnboardingPageFeature

@Immutable
public interface AiutaConsentFeature : AiutaFeature

public inline fun AiutaTryOnFeatures.Builder.builtInConsent(
    block: AiutaConsentBuiltInWithOnboardingPageFeature.Builder.() -> Unit,
): AiutaTryOnFeatures.Builder = apply {
    consent = AiutaConsentBuiltInWithOnboardingPageFeature.Builder().apply(block).build()
}

public inline fun AiutaTryOnFeatures.Builder.standaloneConsent(
    block: AiutaConsentStandaloneOnboardingPageFeature.Builder.() -> Unit,
): AiutaTryOnFeatures.Builder = apply {
    consent = AiutaConsentStandaloneOnboardingPageFeature.Builder().apply(block).build()
}

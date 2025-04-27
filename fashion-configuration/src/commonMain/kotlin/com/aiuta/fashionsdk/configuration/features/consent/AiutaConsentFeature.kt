package com.aiuta.fashionsdk.configuration.features.consent

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.features.consent.builtin.AiutaConsentBuiltInWithOnboardingPageFeature
import com.aiuta.fashionsdk.configuration.features.consent.standalone.AiutaConsentStandaloneOnboardingPageFeature

@Immutable
public interface AiutaConsentFeature : AiutaFeature

public inline fun AiutaFeatures.Builder.builtInConsent(
    block: AiutaConsentBuiltInWithOnboardingPageFeature.Builder.() -> Unit,
): AiutaFeatures.Builder = apply {
    consent = AiutaConsentBuiltInWithOnboardingPageFeature.Builder().apply(block).build()
}

public inline fun AiutaFeatures.Builder.standaloneConsent(
    block: AiutaConsentStandaloneOnboardingPageFeature.Builder.() -> Unit,
): AiutaFeatures.Builder = apply {
    consent = AiutaConsentStandaloneOnboardingPageFeature.Builder().apply(block).build()
}

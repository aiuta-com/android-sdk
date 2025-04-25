package com.aiuta.fashionsdk.configuration.features.features.consent

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.features.features.AiutaTryOnConfigurationFeature
import com.aiuta.fashionsdk.configuration.features.features.AiutaTryOnConfigurationFeatures
import com.aiuta.fashionsdk.configuration.features.features.consent.builtin.AiutaConsentBuiltInWithOnboardingPageFeature
import com.aiuta.fashionsdk.configuration.features.features.consent.standalone.AiutaConsentStandaloneOnboardingPageFeature

@Immutable
public interface AiutaConsentFeature : AiutaTryOnConfigurationFeature

public inline fun AiutaTryOnConfigurationFeatures.Builder.builtInConsent(
    block: AiutaConsentBuiltInWithOnboardingPageFeature.Builder.() -> Unit,
): AiutaTryOnConfigurationFeatures.Builder = apply {
    consent = AiutaConsentBuiltInWithOnboardingPageFeature.Builder().apply(block).build()
}

public inline fun AiutaTryOnConfigurationFeatures.Builder.standaloneConsent(
    block: AiutaConsentStandaloneOnboardingPageFeature.Builder.() -> Unit,
): AiutaTryOnConfigurationFeatures.Builder = apply {
    consent = AiutaConsentStandaloneOnboardingPageFeature.Builder().apply(block).build()
}

package com.aiuta.fashionsdk.configuration.features.consent

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.AiutaFeatures

@Immutable
public sealed interface AiutaConsentFeature : AiutaFeature

public inline fun AiutaFeatures.Builder.embeddedConsent(
    block: AiutaConsentEmbeddedIntoOnboardingFeature.Builder.() -> Unit,
): AiutaFeatures.Builder = apply {
    consent = AiutaConsentEmbeddedIntoOnboardingFeature.Builder().apply(block).build()
}

public inline fun AiutaFeatures.Builder.standaloneOnboardingConsent(
    block: AiutaConsentStandaloneOnboardingPageFeature.Builder.() -> Unit,
): AiutaFeatures.Builder = apply {
    consent = AiutaConsentStandaloneOnboardingPageFeature.Builder().apply(block).build()
}

public inline fun AiutaFeatures.Builder.standaloneImagePickerConsent(
    block: AiutaConsentStandaloneImagePickerPageFeature.Builder.() -> Unit,
): AiutaFeatures.Builder = apply {
    consent = AiutaConsentStandaloneImagePickerPageFeature.Builder().apply(block).build()
}

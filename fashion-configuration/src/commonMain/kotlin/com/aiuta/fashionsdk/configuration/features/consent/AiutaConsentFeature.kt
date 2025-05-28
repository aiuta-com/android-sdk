package com.aiuta.fashionsdk.configuration.features.consent

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.AiutaFeatures

/**
 * Sealed interface for consent feature implementations.
 *
 * This interface represents different ways of implementing user consent collection
 * in the fashion SDK, such as embedded consent in onboarding or standalone consent pages.
 */
@Immutable
public sealed interface AiutaConsentFeature : AiutaFeature

/**
 * DSL function for configuring embedded consent in the onboarding flow.
 *
 * This function allows for DSL-style configuration of consent collection
 * that is integrated into the onboarding process.
 *
 * ```kotlin
 * features {
 *     embeddedConsent {
 *         // Configure embedded consent
 *     }
 * }
 * ```
 *
 * @param block Configuration block for embedded consent
 * @return The updated features builder
 */
public inline fun AiutaFeatures.Builder.embeddedConsent(
    block: AiutaConsentEmbeddedIntoOnboardingFeature.Builder.() -> Unit,
): AiutaFeatures.Builder = apply {
    consent = AiutaConsentEmbeddedIntoOnboardingFeature.Builder().apply(block).build()
}

/**
 * DSL function for configuring standalone consent in the onboarding page.
 *
 * This function allows for DSL-style configuration of a standalone consent page
 * that appears during the onboarding process.
 *
 * ```kotlin
 * features {
 *     standaloneOnboardingConsent {
 *         // Configure standalone onboarding consent
 *     }
 * }
 * ```
 *
 * @param block Configuration block for standalone onboarding consent
 * @return The updated features builder
 */
public inline fun AiutaFeatures.Builder.standaloneOnboardingConsent(
    block: AiutaConsentStandaloneOnboardingPageFeature.Builder.() -> Unit,
): AiutaFeatures.Builder = apply {
    consent = AiutaConsentStandaloneOnboardingPageFeature.Builder().apply(block).build()
}

/**
 * DSL function for configuring standalone consent in the image picker page.
 *
 * This function allows for DSL-style configuration of a standalone consent page
 * that appears when selecting images.
 *
 * ```kotlin
 * features {
 *     standaloneImagePickerConsent {
 *         // Configure standalone image picker consent
 *     }
 * }
 * ```
 *
 * @param block Configuration block for standalone image picker consent
 * @return The updated features builder
 */
public inline fun AiutaFeatures.Builder.standaloneImagePickerConsent(
    block: AiutaConsentStandaloneImagePickerPageFeature.Builder.() -> Unit,
): AiutaFeatures.Builder = apply {
    consent = AiutaConsentStandaloneImagePickerPageFeature.Builder().apply(block).build()
}

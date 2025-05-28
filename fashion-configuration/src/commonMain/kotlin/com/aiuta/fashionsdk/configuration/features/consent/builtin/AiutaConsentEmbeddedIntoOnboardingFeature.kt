package com.aiuta.fashionsdk.configuration.features.consent

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.consent.AiutaConsentFeature
import com.aiuta.fashionsdk.configuration.features.consent.builtin.strings.AiutaConsentEmbeddedIntoOnboardingFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

/**
 * Configuration for consent collection embedded in the onboarding flow.
 * 
 * This feature integrates user consent collection directly into the onboarding process,
 * providing a seamless experience for users to grant necessary permissions.
 * 
 * Required components:
 * - [strings]: Text content for the embedded consent interface
 * 
 * @property strings Text content for the embedded consent interface
 */
public class AiutaConsentEmbeddedIntoOnboardingFeature(
    public val strings: AiutaConsentEmbeddedIntoOnboardingFeatureStrings,
) : AiutaConsentFeature {

    /**
     * Builder for creating [AiutaConsentEmbeddedIntoOnboardingFeature] instances.
     * 
     * This builder ensures all required properties are set before creating the feature instance.
     */
    public class Builder : AiutaFeature.Builder {
        public var strings: AiutaConsentEmbeddedIntoOnboardingFeatureStrings? = null

        public override fun build(): AiutaConsentEmbeddedIntoOnboardingFeature {
            val parentClass = "AiutaConsentEmbeddedIntoOnboardingFeature"

            return AiutaConsentEmbeddedIntoOnboardingFeature(
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
            )
        }
    }
}

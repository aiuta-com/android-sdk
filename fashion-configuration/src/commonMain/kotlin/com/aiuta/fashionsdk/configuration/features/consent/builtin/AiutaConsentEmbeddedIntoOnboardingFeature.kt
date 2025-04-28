package com.aiuta.fashionsdk.configuration.features.consent

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.consent.AiutaConsentFeature
import com.aiuta.fashionsdk.configuration.features.consent.builtin.strings.AiutaConsentEmbeddedIntoOnboardingFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaConsentEmbeddedIntoOnboardingFeature private constructor(
    public val strings: AiutaConsentEmbeddedIntoOnboardingFeatureStrings,
) : AiutaConsentFeature {

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

package com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.builtin

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.AiutaConsentFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.builtin.strings.AiutaConsentBuiltInWithOnboardingPageFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

public class AiutaConsentBuiltInWithOnboardingPageFeature private constructor(
    public val strings: AiutaConsentBuiltInWithOnboardingPageFeatureStrings,
) : AiutaConsentFeature {

    public class Builder : AiutaFeature.Builder {
        public var strings: AiutaConsentBuiltInWithOnboardingPageFeatureStrings? = null

        public override fun build(): AiutaConsentBuiltInWithOnboardingPageFeature {
            val parentClass = "AiutaConsentBuiltInWithOnboardingPageFeature"

            return AiutaConsentBuiltInWithOnboardingPageFeature(
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
            )
        }
    }
}

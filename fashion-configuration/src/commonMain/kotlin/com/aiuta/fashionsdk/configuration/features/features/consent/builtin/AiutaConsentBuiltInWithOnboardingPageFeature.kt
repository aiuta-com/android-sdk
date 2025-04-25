package com.aiuta.fashionsdk.configuration.features.features.consent.builtin

import com.aiuta.fashionsdk.configuration.features.features.AiutaTryOnConfigurationFeature
import com.aiuta.fashionsdk.configuration.features.features.consent.AiutaConsentFeature
import com.aiuta.fashionsdk.configuration.features.features.consent.builtin.strings.AiutaConsentBuiltInWithOnboardingPageFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaConsentBuiltInWithOnboardingPageFeature private constructor(
    public val strings: AiutaConsentBuiltInWithOnboardingPageFeatureStrings,
) : AiutaConsentFeature {

    public class Builder : AiutaTryOnConfigurationFeature.Builder {
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

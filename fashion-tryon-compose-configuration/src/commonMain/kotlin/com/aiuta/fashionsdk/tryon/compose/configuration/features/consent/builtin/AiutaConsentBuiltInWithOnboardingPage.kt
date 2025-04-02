package com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.builtin

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.AiutaConsentFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.builtin.strings.AiutaConsentBuiltInWithOnboardingPageStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

public class AiutaConsentBuiltInWithOnboardingPage private constructor(
    public val strings: AiutaConsentBuiltInWithOnboardingPageStrings,
) : AiutaConsentFeature {

    public class Builder : AiutaFeature.Builder {
        public var strings: AiutaConsentBuiltInWithOnboardingPageStrings? = null

        public override fun build(): AiutaConsentBuiltInWithOnboardingPage {
            val parentClass = "AiutaConsentBuiltInWithOnboardingPage"

            return AiutaConsentBuiltInWithOnboardingPage(
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
            )
        }
    }
}

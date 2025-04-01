package com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.builtin

import com.aiuta.fashionsdk.tryon.compose.configuration.annotations.AiutaTryOnConfigurationDsl
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.AiutaConsentFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.builtin.strings.AiutaConsentBuiltInWithOnboardingPageStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

public class AiutaConsentBuiltInWithOnboardingPage(
    public val strings: AiutaConsentBuiltInWithOnboardingPageStrings,
) : AiutaConsentFeature {
    @AiutaTryOnConfigurationDsl
    public class Builder : AiutaConsentFeature.Builder {
        public var strings: AiutaConsentBuiltInWithOnboardingPageStrings? = null

        public override fun build(): AiutaConsentBuiltInWithOnboardingPage {
            val parentClass = "AiutaConsentBuiltInWithOnboardingPage"

            return AiutaConsentBuiltInWithOnboardingPage(
                strings =
                    this.strings.checkNotNullWithDescription(
                        parentClass = parentClass,
                        property = "strings",
                    ),
            )
        }
    }
}

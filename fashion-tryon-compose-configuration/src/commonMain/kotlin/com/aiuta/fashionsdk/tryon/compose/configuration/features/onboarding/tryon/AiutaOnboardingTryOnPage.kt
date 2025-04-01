package com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.tryon

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.configuration.annotations.AiutaTryOnConfigurationDsl
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.tryon.images.AiutaOnboardingTryOnPageImages
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.tryon.strings.AiutaOnboardingTryOnPageStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

@Immutable
public class AiutaOnboardingTryOnPage(
    public val images: AiutaOnboardingTryOnPageImages,
    public val strings: AiutaOnboardingTryOnPageStrings,
) {
    @AiutaTryOnConfigurationDsl
    public class Builder {
        public var images: AiutaOnboardingTryOnPageImages? = null
        public var strings: AiutaOnboardingTryOnPageStrings? = null

        public fun build(): AiutaOnboardingTryOnPage {
            val parentClass = "AiutaOnboardingTryOnPage"

            return AiutaOnboardingTryOnPage(
                images =
                    this.images.checkNotNullWithDescription(
                        parentClass = parentClass,
                        property = "images",
                    ),
                strings =
                    this.strings.checkNotNullWithDescription(
                        parentClass = parentClass,
                        property = "strings",
                    ),
            )
        }
    }
}

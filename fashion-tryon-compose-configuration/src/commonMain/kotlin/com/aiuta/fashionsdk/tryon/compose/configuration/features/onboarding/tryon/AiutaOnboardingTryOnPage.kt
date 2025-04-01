package com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.tryon

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.annotations.AiutaDsl
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.tryon.images.AiutaOnboardingTryOnPageImages
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.tryon.strings.AiutaOnboardingTryOnPageStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

@Immutable
public class AiutaOnboardingTryOnPage(
    public val images: AiutaOnboardingTryOnPageImages,
    public val strings: AiutaOnboardingTryOnPageStrings,
) {
    @AiutaDsl
    public class Builder {
        public var images: AiutaOnboardingTryOnPageImages? = null
        public var strings: AiutaOnboardingTryOnPageStrings? = null

        public fun build(): AiutaOnboardingTryOnPage {
            val parentClass = "AiutaOnboardingTryOnPage"

            return AiutaOnboardingTryOnPage(
                images = images.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "images",
                ),
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
            )
        }
    }
}

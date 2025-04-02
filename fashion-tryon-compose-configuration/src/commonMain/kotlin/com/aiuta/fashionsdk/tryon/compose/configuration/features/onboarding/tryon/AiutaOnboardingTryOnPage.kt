package com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.tryon

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.AiutaOnboardingFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.tryon.images.AiutaOnboardingTryOnPageImages
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.tryon.strings.AiutaOnboardingTryOnPageStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

@Immutable
public class AiutaOnboardingTryOnPage(
    public val images: AiutaOnboardingTryOnPageImages,
    public val strings: AiutaOnboardingTryOnPageStrings,
) : AiutaFeature {

    public class Builder : AiutaFeature.Builder {
        public var images: AiutaOnboardingTryOnPageImages? = null
        public var strings: AiutaOnboardingTryOnPageStrings? = null

        public override fun build(): AiutaOnboardingTryOnPage {
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

public inline fun AiutaOnboardingFeature.Builder.tryOnPage(
    block: AiutaOnboardingTryOnPage.Builder.() -> Unit,
) {
    tryOnPage = AiutaOnboardingTryOnPage.Builder().apply(block).build()
}

package com.aiuta.fashionsdk.configuration.features.onboarding.tryon

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.onboarding.AiutaOnboardingFeature
import com.aiuta.fashionsdk.configuration.features.onboarding.tryon.images.AiutaOnboardingTryOnPageFeatureImages
import com.aiuta.fashionsdk.configuration.features.onboarding.tryon.strings.AiutaOnboardingTryOnPageFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

@Immutable
public class AiutaOnboardingTryOnPageFeature private constructor(
    public val images: AiutaOnboardingTryOnPageFeatureImages,
    public val strings: AiutaOnboardingTryOnPageFeatureStrings,
) : AiutaFeature {

    public class Builder : AiutaFeature.Builder {
        public var images: AiutaOnboardingTryOnPageFeatureImages? = null
        public var strings: AiutaOnboardingTryOnPageFeatureStrings? = null

        public override fun build(): AiutaOnboardingTryOnPageFeature {
            val parentClass = "AiutaOnboardingTryOnPage"

            return AiutaOnboardingTryOnPageFeature(
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
    block: AiutaOnboardingTryOnPageFeature.Builder.() -> Unit,
): AiutaOnboardingFeature.Builder = apply {
    tryOnPage = AiutaOnboardingTryOnPageFeature.Builder().apply(block).build()
}

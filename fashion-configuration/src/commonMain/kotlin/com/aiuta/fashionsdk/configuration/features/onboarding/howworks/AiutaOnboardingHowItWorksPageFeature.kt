package com.aiuta.fashionsdk.configuration.features.onboarding.howworks

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.onboarding.AiutaOnboardingFeature
import com.aiuta.fashionsdk.configuration.features.onboarding.howworks.images.AiutaOnboardingHowItWorksPageFeatureImages
import com.aiuta.fashionsdk.configuration.features.onboarding.howworks.strings.AiutaOnboardingHowItWorksPageFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

@Immutable
public class AiutaOnboardingHowItWorksPageFeature(
    public val images: AiutaOnboardingHowItWorksPageFeatureImages,
    public val strings: AiutaOnboardingHowItWorksPageFeatureStrings,
) : AiutaFeature {

    public class Builder : AiutaFeature.Builder {
        public var images: AiutaOnboardingHowItWorksPageFeatureImages? = null
        public var strings: AiutaOnboardingHowItWorksPageFeatureStrings? = null

        public override fun build(): AiutaOnboardingHowItWorksPageFeature {
            val parentClass = "AiutaOnboardingHowItWorksPageFeature"

            return AiutaOnboardingHowItWorksPageFeature(
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

public inline fun AiutaOnboardingFeature.Builder.howItWorksPage(
    block: AiutaOnboardingHowItWorksPageFeature.Builder.() -> Unit,
): AiutaOnboardingFeature.Builder = apply {
    this.howItWorksPage = AiutaOnboardingHowItWorksPageFeature.Builder().apply(block).build()
}

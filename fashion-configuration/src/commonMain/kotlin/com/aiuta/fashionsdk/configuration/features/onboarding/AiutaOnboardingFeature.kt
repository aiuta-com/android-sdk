package com.aiuta.fashionsdk.configuration.features.onboarding

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.features.onboarding.bestresult.AiutaOnboardingBestResultsPageFeature
import com.aiuta.fashionsdk.configuration.features.onboarding.howworks.AiutaOnboardingHowItWorksPageFeature
import com.aiuta.fashionsdk.configuration.features.onboarding.shapes.AiutaOnboardingFeatureShapes
import com.aiuta.fashionsdk.configuration.features.onboarding.strings.AiutaOnboardingFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

@Immutable
public class AiutaOnboardingFeature private constructor(
    // Features
    public val howItWorksPage: AiutaOnboardingHowItWorksPageFeature,
    public val bestResultsPage: AiutaOnboardingBestResultsPageFeature? = null,
    // General
    public val strings: AiutaOnboardingFeatureStrings,
    public val shapes: AiutaOnboardingFeatureShapes,
) : AiutaFeature {

    public class Builder : AiutaFeature.Builder {
        public var howItWorksPage: AiutaOnboardingHowItWorksPageFeature? = null
        public var bestResultsPage: AiutaOnboardingBestResultsPageFeature? = null
        public var strings: AiutaOnboardingFeatureStrings? = null
        public var shapes: AiutaOnboardingFeatureShapes? = null

        public override fun build(): AiutaOnboardingFeature {
            val parentClass = "AiutaOnboardingFeature"

            return AiutaOnboardingFeature(
                howItWorksPage = howItWorksPage.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "howItWorksPage",
                ),
                bestResultsPage = this.bestResultsPage,
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
                shapes = shapes.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "shapes",
                ),
            )
        }
    }
}

public inline fun AiutaFeatures.Builder.onboarding(
    block: AiutaOnboardingFeature.Builder.() -> Unit,
): AiutaFeatures.Builder = apply {
    onboarding = AiutaOnboardingFeature.Builder().apply(block).build()
}

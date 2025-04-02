package com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.bestresult.AiutaOnboardingBestResultsPage
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.shapes.AiutaOnboardingFeatureShapes
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.strings.AiutaOnboardingFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.tryon.AiutaOnboardingTryOnPage
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

@Immutable
public class AiutaOnboardingFeature private constructor(
    // Features
    public val tryOnPage: AiutaOnboardingTryOnPage,
    public val bestResultsPage: AiutaOnboardingBestResultsPage? = null,
    // General
    public val strings: AiutaOnboardingFeatureStrings,
    public val shapes: AiutaOnboardingFeatureShapes,
) : AiutaFeature {

    public class Builder : AiutaFeature.Builder {
        public var tryOnPage: AiutaOnboardingTryOnPage? = null
        public var bestResultsPage: AiutaOnboardingBestResultsPage? = null
        public var strings: AiutaOnboardingFeatureStrings? = null
        public var shapes: AiutaOnboardingFeatureShapes? = null

        public override fun build(): AiutaOnboardingFeature {
            val parentClass = "AiutaOnboardingFeature"

            return AiutaOnboardingFeature(
                tryOnPage = tryOnPage.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "tryOnPage",
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

public inline fun AiutaTryOnFeatures.Builder.onboarding(
    block: AiutaOnboardingFeature.Builder.() -> Unit,
) {
    onboarding = AiutaOnboardingFeature.Builder().apply(block).build()
}

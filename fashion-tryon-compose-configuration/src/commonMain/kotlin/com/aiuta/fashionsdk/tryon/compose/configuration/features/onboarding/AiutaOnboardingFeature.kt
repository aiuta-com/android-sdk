package com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.bestresult.AiutaOnboardingBestResultsPageFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.shapes.AiutaOnboardingFeatureShapes
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.strings.AiutaOnboardingFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.tryon.AiutaOnboardingTryOnPageFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

@Immutable
public class AiutaOnboardingFeature private constructor(
    // Features
    public val tryOnPage: AiutaOnboardingTryOnPageFeature,
    public val bestResultsPage: AiutaOnboardingBestResultsPageFeature? = null,
    // General
    public val strings: AiutaOnboardingFeatureStrings,
    public val shapes: AiutaOnboardingFeatureShapes,
) : AiutaTryOnConfigurationFeature {

    public class Builder : AiutaTryOnConfigurationFeature.Builder {
        public var tryOnPage: AiutaOnboardingTryOnPageFeature? = null
        public var bestResultsPage: AiutaOnboardingBestResultsPageFeature? = null
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

public inline fun AiutaTryOnConfigurationFeatures.Builder.onboarding(
    block: AiutaOnboardingFeature.Builder.() -> Unit,
): AiutaTryOnConfigurationFeatures.Builder = apply {
    onboarding = AiutaOnboardingFeature.Builder().apply(block).build()
}

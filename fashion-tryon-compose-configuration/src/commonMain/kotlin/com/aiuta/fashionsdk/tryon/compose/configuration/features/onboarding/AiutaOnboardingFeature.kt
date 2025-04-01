package com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.configuration.annotations.AiutaTryOnConfigurationDsl
import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.bestresult.AiutaOnboardingBestResultsPage
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.shapes.AiutaOnboardingFeatureShapes
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.strings.AiutaOnboardingFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.tryon.AiutaOnboardingTryOnPage
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

@Immutable
public class AiutaOnboardingFeature(
    public val tryOnPage: AiutaOnboardingTryOnPage,
    public val bestResultsPage: AiutaOnboardingBestResultsPage? = null,
    public val strings: AiutaOnboardingFeatureStrings,
    public val shapes: AiutaOnboardingFeatureShapes,
) {
    @AiutaTryOnConfigurationDsl
    public class Builder {
        public var tryOnPage: AiutaOnboardingTryOnPage? = null
        public var bestResultsPage: AiutaOnboardingBestResultsPage? = null
        public var strings: AiutaOnboardingFeatureStrings? = null
        public var shapes: AiutaOnboardingFeatureShapes? = null

        public inline fun tryOnPage(block: AiutaOnboardingTryOnPage.Builder.() -> Unit) {
            tryOnPage = AiutaOnboardingTryOnPage.Builder().apply(block).build()
        }

        public inline fun bestResultsPage(
            block: AiutaOnboardingBestResultsPage.Builder.() -> Unit,
        ) {
            bestResultsPage = AiutaOnboardingBestResultsPage.Builder().apply(block).build()
        }

        public fun build(): AiutaOnboardingFeature {
            val parentClass = "AiutaOnboardingFeature"

            return AiutaOnboardingFeature(
                tryOnPage =
                    this.tryOnPage.checkNotNullWithDescription(
                        parentClass = parentClass,
                        property = "tryOnPage",
                    ),
                bestResultsPage = this.bestResultsPage,
                strings =
                    this.strings.checkNotNullWithDescription(
                        parentClass = parentClass,
                        property = "strings",
                    ),
                shapes =
                    this.shapes.checkNotNullWithDescription(
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

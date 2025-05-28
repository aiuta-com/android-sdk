package com.aiuta.fashionsdk.configuration.features.onboarding.howworks

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.onboarding.AiutaOnboardingFeature
import com.aiuta.fashionsdk.configuration.features.onboarding.howworks.images.AiutaOnboardingHowItWorksPageFeatureImages
import com.aiuta.fashionsdk.configuration.features.onboarding.howworks.strings.AiutaOnboardingHowItWorksPageFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

/**
 * Configuration for the "How It Works" page in the onboarding flow.
 *
 * This feature provides a step-by-step explanation of how to use the app's
 * main functionality, using illustrations and descriptive text.
 *
 * Required components:
 * - [images]: Illustrations showing how to use the app
 * - [strings]: Text strings explaining each step
 */
@Immutable
public class AiutaOnboardingHowItWorksPageFeature(
    public val images: AiutaOnboardingHowItWorksPageFeatureImages,
    public val strings: AiutaOnboardingHowItWorksPageFeatureStrings,
) : AiutaFeature {

    /**
     * Builder class for creating instances of [AiutaOnboardingHowItWorksPageFeature].
     *
     * This builder ensures that all required components are provided before
     * creating the feature instance.
     */
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

/**
 * DSL function for configuring the "How It Works" page in the onboarding flow.
 *
 * Example usage:
 * ```
 * onboarding {
 *     howItWorksPage {
 *         images = ...
 *         strings = ...
 *     }
 * }
 * ```
 */
public inline fun AiutaOnboardingFeature.Builder.howItWorksPage(
    block: AiutaOnboardingHowItWorksPageFeature.Builder.() -> Unit,
): AiutaOnboardingFeature.Builder = apply {
    this.howItWorksPage = AiutaOnboardingHowItWorksPageFeature.Builder().apply(block).build()
}

package com.aiuta.fashionsdk.configuration.features.tryon.feedback.other

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.tryon.feedback.AiutaTryOnFeedbackFeature
import com.aiuta.fashionsdk.configuration.features.tryon.feedback.other.strings.AiutaTryOnFeedbackOtherFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

/**
 * Configuration for additional feedback options in the try-on feature.
 *
 * This class provides configuration for custom feedback options beyond the standard ones,
 * allowing for more specific feedback collection.
 *
 * @property strings Text strings for the additional feedback options
 */
public class AiutaTryOnFeedbackOtherFeature(
    public val strings: AiutaTryOnFeedbackOtherFeatureStrings,
) : AiutaFeature {

    /**
     * Builder for creating [AiutaTryOnFeedbackOtherFeature] instances.
     *
     * This builder ensures all required properties are set before creating the feature instance.
     */
    public class Builder : AiutaFeature.Builder {
        public var strings: AiutaTryOnFeedbackOtherFeatureStrings? = null

        public override fun build(): AiutaTryOnFeedbackOtherFeature {
            val parentClass = "AiutaTryOnFeedbackOtherFeature"

            return AiutaTryOnFeedbackOtherFeature(
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
            )
        }
    }
}

/**
 * DSL function for configuring additional feedback options.
 *
 * This function allows for DSL-style configuration of additional feedback options
 * within the try-on feature's feedback configuration.
 *
 * ```kotlin
 * feedback {
 *     otherFeedback {
 *         strings = AiutaTryOnFeedbackOtherFeatureStrings.Default()
 *     }
 * }
 * ```
 *
 * @param block Configuration block for the additional feedback options
 * @return The updated feedback feature builder
 */
public inline fun AiutaTryOnFeedbackFeature.Builder.otherFeedback(
    block: AiutaTryOnFeedbackOtherFeature.Builder.() -> Unit,
): AiutaTryOnFeedbackFeature.Builder = apply {
    otherFeedback = AiutaTryOnFeedbackOtherFeature.Builder().apply(block).build()
}

package com.aiuta.fashionsdk.configuration.features.tryon.feedback

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.tryon.AiutaTryOnFeature
import com.aiuta.fashionsdk.configuration.features.tryon.feedback.icons.AiutaTryOnFeedbackFeatureIcons
import com.aiuta.fashionsdk.configuration.features.tryon.feedback.other.AiutaTryOnFeedbackOtherFeature
import com.aiuta.fashionsdk.configuration.features.tryon.feedback.strings.AiutaTryOnFeedbackFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

/**
 * Configuration for the feedback collection in the try-on feature.
 *
 * This feature manages the collection of user feedback about the try-on experience,
 * including general feedback and specific feedback about try-on results.
 *
 * Required components:
 * - [icons]: Icon resources for feedback UI
 * - [strings]: Text content for feedback interface
 *
 * Optional components:
 * - [otherFeedback]: Additional feedback collection options
 *
 * @property otherFeedback Optional configuration for additional feedback options
 * @property icons Icon resources for feedback UI elements
 * @property strings Text content for feedback interface
 */
public class AiutaTryOnFeedbackFeature(
    // Features
    public val otherFeedback: AiutaTryOnFeedbackOtherFeature?,
    // General
    public val icons: AiutaTryOnFeedbackFeatureIcons,
    public val strings: AiutaTryOnFeedbackFeatureStrings,
) : AiutaFeature {

    /**
     * Builder for creating [AiutaTryOnFeedbackFeature] instances.
     */
    public class Builder : AiutaFeature.Builder {
        public var otherFeedback: AiutaTryOnFeedbackOtherFeature? = null
        public var icons: AiutaTryOnFeedbackFeatureIcons? = null
        public var strings: AiutaTryOnFeedbackFeatureStrings? = null

        public override fun build(): AiutaTryOnFeedbackFeature {
            val parentClass = "AiutaTryOnFeedbackFeature"

            return AiutaTryOnFeedbackFeature(
                otherFeedback = otherFeedback,
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
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
 * DSL function for configuring the feedback feature.
 *
 * This function allows for DSL-style configuration of the feedback feature
 * within the try-on feature configuration.
 *
 * ```kotlin
 * tryOn {
 *     feedback {
 *         icons = ...
 *         strings = ...
 *         // Optional feedback configuration
 *         otherFeedback { /* Configuration */ }
 *     }
 * }
 * ```
 *
 * @param block Configuration block for the feedback feature
 * @return The updated [AiutaTryOnFeature.Builder]
 */
public inline fun AiutaTryOnFeature.Builder.feedback(
    block: AiutaTryOnFeedbackFeature.Builder.() -> Unit,
): AiutaTryOnFeature.Builder = apply {
    feedback = AiutaTryOnFeedbackFeature.Builder().apply(block).build()
}

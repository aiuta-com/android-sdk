package com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.feedback

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.AiutaTryOnFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.feedback.icons.AiutaTryOnFeedbackFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.feedback.other.AiutaTryOnFeedbackOtherFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.feedback.strings.AiutaTryOnFeedbackFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.utils.checkNotNullWithDescription

public class AiutaTryOnFeedbackFeature private constructor(
    // Features
    public val otherFeedback: AiutaTryOnFeedbackOtherFeature?,
    // General
    public val icons: AiutaTryOnFeedbackFeatureIcons,
    public val strings: AiutaTryOnFeedbackFeatureStrings,
) : AiutaTryOnConfigurationFeature {

    public class Builder : AiutaTryOnConfigurationFeature.Builder {
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

public inline fun AiutaTryOnFeature.Builder.feedback(
    block: AiutaTryOnFeedbackFeature.Builder.() -> Unit,
): AiutaTryOnFeature.Builder = apply {
    feedback = AiutaTryOnFeedbackFeature.Builder().apply(block).build()
}

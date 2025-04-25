package com.aiuta.fashionsdk.configuration.features.tryon.feedback.other

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.tryon.feedback.AiutaTryOnFeedbackFeature
import com.aiuta.fashionsdk.configuration.features.tryon.feedback.other.strings.AiutaTryOnFeedbackOtherFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaTryOnFeedbackOtherFeature private constructor(
    public val strings: AiutaTryOnFeedbackOtherFeatureStrings,
) : AiutaFeature {

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

public inline fun AiutaTryOnFeedbackFeature.Builder.otherFeedback(
    block: AiutaTryOnFeedbackOtherFeature.Builder.() -> Unit,
): AiutaTryOnFeedbackFeature.Builder = apply {
    otherFeedback = AiutaTryOnFeedbackOtherFeature.Builder().apply(block).build()
}

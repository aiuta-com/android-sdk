package com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.feedback.other.strings

import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.models.AiutaStringValidationContainer

public interface AiutaTryOnFeedbackOtherFeatureStrings {
    public val otherFeedbackTitle: String
    public val otherFeedbackButtonSend: String
    public val otherFeedbackButtonCancel: String
    public val otherFeedbackOptionOther: String

    public class Default : AiutaTryOnFeedbackOtherFeatureStrings {
        override val otherFeedbackTitle: String = "Tell us what we could improve?"
        override val otherFeedbackButtonSend: String = "Send feedback"
        override val otherFeedbackButtonCancel: String = "Cancel"
        override val otherFeedbackOptionOther: String = "Other"
    }
}

internal val AiutaTryOnFeedbackOtherFeatureStrings.validationList
    get() = listOf(
        AiutaStringValidationContainer(
            propertyName = "otherFeedbackTitle",
            string = otherFeedbackTitle,
        ),
        AiutaStringValidationContainer(
            propertyName = "otherFeedbackButtonSend",
            string = otherFeedbackButtonSend,
        ),
        AiutaStringValidationContainer(
            propertyName = "otherFeedbackButtonCancel",
            string = otherFeedbackButtonCancel,
        ),
        AiutaStringValidationContainer(
            propertyName = "otherFeedbackOptionOther",
            string = otherFeedbackOptionOther,
        ),
    )

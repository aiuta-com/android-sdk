package com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.feedback.strings

import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.models.AiutaStringValidationContainer

public interface AiutaTryOnFeedbackFeatureStrings {
    public val tryOnFeedbackOptions: List<String>
    public val tryOnFeedbackTitle: String
    public val tryOnFeedbackButtonSkip: String
    public val tryOnFeedbackButtonSend: String
    public val tryOnFeedbackGratitudeText: String

    public class Default : AiutaTryOnFeedbackFeatureStrings {
        override val tryOnFeedbackOptions: List<String> = listOf(
            "This style isn’t for me",
            "The item looks off",
            "I look different",
        )
        override val tryOnFeedbackTitle: String = "Can you tell us more?"
        override val tryOnFeedbackButtonSkip: String = "Skip"
        override val tryOnFeedbackButtonSend: String = "Send"
        override val tryOnFeedbackGratitudeText: String = "Thank you for your feedback"
    }
}

internal val AiutaTryOnFeedbackFeatureStrings.validationList
    get() = tryOnFeedbackOptions.mapIndexed { index, string ->
        AiutaStringValidationContainer(
            propertyName = "tryOnFeedbackOptions[$index]",
            string = string,
        )
    } + listOf(
        AiutaStringValidationContainer(
            propertyName = "tryOnFeedbackTitle",
            string = tryOnFeedbackTitle,
        ),
        AiutaStringValidationContainer(
            propertyName = "tryOnFeedbackButtonSkip",
            string = tryOnFeedbackButtonSkip,
        ),
        AiutaStringValidationContainer(
            propertyName = "tryOnFeedbackButtonSend",
            string = tryOnFeedbackButtonSend,
        ),
        AiutaStringValidationContainer(
            propertyName = "tryOnFeedbackGratitudeText",
            string = tryOnFeedbackGratitudeText,
        ),
    )

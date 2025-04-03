package com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.feedback.strings

public interface AiutaTryOnFeedbackFeatureStrings {
    public val tryOnFeedbackOptions: List<String>
    public val tryOnFeedbackButtonSkip: String
    public val tryOnFeedbackButtonSend: String
    public val tryOnFeedbackButtonSendFeedback: String
    public val tryOnFeedbackGratitudeText: String

    public class Default : AiutaTryOnFeedbackFeatureStrings {
        override val tryOnFeedbackOptions: List<String> = listOf(
            "This style isn’t for me",
            "The item looks off",
            "I look different",
        )
        override val tryOnFeedbackButtonSkip: String = "Skip"
        override val tryOnFeedbackButtonSend: String = "Send"
        override val tryOnFeedbackButtonSendFeedback: String = "Send feedback"
        override val tryOnFeedbackGratitudeText: String = "Thank you for your feedback"
    }
}

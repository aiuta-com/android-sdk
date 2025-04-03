package com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.feedback.strings

public interface AiutaTryOnFeedbackFeatureStrings {
    public val tryOnFeedbackButtonSkip: String
    public val tryOnFeedbackButtonSend: String
    public val tryOnFeedbackButtonSendFeedback: String

    public class Default : AiutaTryOnFeedbackFeatureStrings {
        override val tryOnFeedbackButtonSkip: String = "Skip"
        override val tryOnFeedbackButtonSend: String = "Send"
        override val tryOnFeedbackButtonSendFeedback: String = "Send feedback"
    }
}

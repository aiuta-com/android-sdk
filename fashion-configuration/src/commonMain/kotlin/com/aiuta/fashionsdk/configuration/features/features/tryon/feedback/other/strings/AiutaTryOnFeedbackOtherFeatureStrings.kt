package com.aiuta.fashionsdk.configuration.features.features.tryon.feedback.other.strings

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

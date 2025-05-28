package com.aiuta.fashionsdk.configuration.features.tryon.feedback.other.strings

/**
 * Interface for additional feedback-related text strings.
 * 
 * This interface defines the text strings used in the additional feedback interface,
 * allowing for customization of user-facing text in the extended feedback UI.
 * 
 * @property otherFeedbackTitle Title text displayed at the top of the additional feedback screen
 * @property otherFeedbackButtonSend Text for the send feedback button
 * @property otherFeedbackButtonCancel Text for the cancel button
 * @property otherFeedbackOptionOther Text for the "Other" feedback option
 */
public interface AiutaTryOnFeedbackOtherFeatureStrings {
    public val otherFeedbackTitle: String
    public val otherFeedbackButtonSend: String
    public val otherFeedbackButtonCancel: String
    public val otherFeedbackOptionOther: String

    /**
     * Default implementation of [AiutaTryOnFeedbackOtherFeatureStrings].
     * 
     * Provides standard English text strings for the additional feedback interface.
     */
    public class Default : AiutaTryOnFeedbackOtherFeatureStrings {
        override val otherFeedbackTitle: String = "Tell us what we could improve?"
        override val otherFeedbackButtonSend: String = "Send feedback"
        override val otherFeedbackButtonCancel: String = "Cancel"
        override val otherFeedbackOptionOther: String = "Other"
    }
}

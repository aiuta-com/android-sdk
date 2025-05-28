package com.aiuta.fashionsdk.configuration.features.tryon.feedback.strings

/**
 * Interface for feedback-related text strings.
 * 
 * This interface defines the text strings used in the feedback interface,
 * allowing for customization of user-facing text in the feedback UI.
 * 
 * @property tryOnFeedbackOptions List of predefined feedback options for users to choose from
 * @property tryOnFeedbackTitle Title text displayed at the top of the feedback screen
 * @property tryOnFeedbackButtonSkip Text for the skip button
 * @property tryOnFeedbackButtonSend Text for the send button
 * @property tryOnFeedbackGratitudeText Thank you message shown after feedback is submitted
 */
public interface AiutaTryOnFeedbackFeatureStrings {
    public val tryOnFeedbackOptions: List<String>
    public val tryOnFeedbackTitle: String
    public val tryOnFeedbackButtonSkip: String
    public val tryOnFeedbackButtonSend: String
    public val tryOnFeedbackGratitudeText: String

    /**
     * Default implementation of [AiutaTryOnFeedbackFeatureStrings].
     * 
     * Provides standard English text strings for the feedback interface.
     */
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

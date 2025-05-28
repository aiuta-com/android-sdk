package com.aiuta.fashionsdk.configuration.features.tryon.history.strings

/**
 * Interface for history-related text strings.
 *
 * This interface defines the text strings used in the generations history interface,
 * allowing for customization of user-facing text in the history UI.
 *
 * @property generationsHistoryPageTitle Title text displayed at the top of the history page
 */
public interface AiutaTryOnGenerationsHistoryFeatureStrings {
    public val generationsHistoryPageTitle: String

    /**
     * Default implementation of [AiutaTryOnGenerationsHistoryFeatureStrings].
     *
     * Provides standard English text strings for the history interface.
     */
    public class Default : AiutaTryOnGenerationsHistoryFeatureStrings {
        override val generationsHistoryPageTitle: String = "History"
    }
}

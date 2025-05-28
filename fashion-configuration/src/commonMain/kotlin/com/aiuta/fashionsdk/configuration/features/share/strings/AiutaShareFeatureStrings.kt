package com.aiuta.fashionsdk.configuration.features.share.strings

/**
 * Interface for share feature text strings.
 * 
 * This interface defines the text strings used in the share interface,
 * allowing for localization and customization of user-facing text.
 * 
 * @property shareButton Text displayed on the share button
 */
public interface AiutaShareFeatureStrings {
    public val shareButton: String

    /**
     * Default implementation of [AiutaShareFeatureStrings].
     */
    public class Default : AiutaShareFeatureStrings {
        override val shareButton: String = "Share"
    }
}

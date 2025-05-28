package com.aiuta.fashionsdk.configuration.features.welcome.strings

/**
 * Interface defining text strings used in the welcome screen.
 * 
 * This interface provides strings for titles, descriptions, and button text
 * displayed on the welcome screen.
 */
public interface AiutaWelcomeScreenFeatureStrings {
    /**
     * Main title displayed at the top of the welcome screen.
     */
    public val welcomeTitle: String

    /**
     * Descriptive text explaining the app's main functionality.
     */
    public val welcomeDescription: String

    /**
     * Text for the main action button on the welcome screen.
     */
    public val welcomeButtonStart: String

    /**
     * Default implementation of [AiutaWelcomeScreenFeatureStrings].
     * 
     * Provides standard English text for the welcome screen.
     */
    public class Default : AiutaWelcomeScreenFeatureStrings {
        override val welcomeTitle: String = "Try on you"
        override val welcomeDescription: String = "Welcome to our Virtual try-on. Try on the item directly on your photo"
        override val welcomeButtonStart: String = "Let’s start"
    }
}

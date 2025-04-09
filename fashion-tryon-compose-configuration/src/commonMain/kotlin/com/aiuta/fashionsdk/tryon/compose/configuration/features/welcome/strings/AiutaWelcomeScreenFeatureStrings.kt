package com.aiuta.fashionsdk.tryon.compose.configuration.features.welcome.strings

public interface AiutaWelcomeScreenFeatureStrings {
    public val welcomeTitle: String
    public val welcomeDescription: String
    public val welcomeButtonStart: String

    public class Default : AiutaWelcomeScreenFeatureStrings {
        override val welcomeTitle: String = "Try on you"
        override val welcomeDescription: String = "Welcome to our Virtual try-on. Try on the item directly on your photo"
        override val welcomeButtonStart: String = "Let’s start"
    }
}

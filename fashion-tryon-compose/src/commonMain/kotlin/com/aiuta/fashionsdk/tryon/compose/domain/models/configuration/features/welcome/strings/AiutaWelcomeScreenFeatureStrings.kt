package com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.welcome.strings

public interface AiutaWelcomeScreenFeatureStrings {
    public val welcomeTitle: String
    public val welcomeDescription: String
    public val welcomeButtonStart: String

    public class Default(
        override val welcomeTitle: String,
        override val welcomeDescription: String,
        override val welcomeButtonStart: String,
    ) : AiutaWelcomeScreenFeatureStrings {

        public constructor() : this(
            welcomeTitle = "Try on you",
            welcomeDescription = "Welcome to our Virtual try-on. Try on the item directly on your photo",
            welcomeButtonStart = "Let’s start",
        )
    }
}

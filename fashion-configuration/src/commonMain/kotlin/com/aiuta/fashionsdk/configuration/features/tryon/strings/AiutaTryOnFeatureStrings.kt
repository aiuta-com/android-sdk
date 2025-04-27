package com.aiuta.fashionsdk.configuration.features.tryon.strings

public interface AiutaTryOnFeatureStrings {
    public val tryOnPageTitle: String
    public val tryOn: String

    public class Default : AiutaTryOnFeatureStrings {
        override val tryOnPageTitle: String = "Virtual Try-on"
        override val tryOn: String = "Try on"
    }
}

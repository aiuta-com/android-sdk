package com.aiuta.fashionsdk.configuration.features.powerby.strings

public interface AiutaPoweredByFeatureStrings {
    public val poweredByAiuta: String

    public class Default : AiutaPoweredByFeatureStrings {
        override val poweredByAiuta: String = "Powered by Aiuta"
    }
}

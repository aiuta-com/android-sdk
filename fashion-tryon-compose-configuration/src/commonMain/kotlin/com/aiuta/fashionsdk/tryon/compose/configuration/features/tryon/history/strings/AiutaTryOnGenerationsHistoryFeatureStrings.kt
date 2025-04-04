package com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.history.strings

public interface AiutaTryOnGenerationsHistoryFeatureStrings {
    public val generationsHistoryPageTitle: String
    public val generationsHistoryButtonSelect: String

    public class Default : AiutaTryOnGenerationsHistoryFeatureStrings {
        override val generationsHistoryPageTitle: String = "History"
        override val generationsHistoryButtonSelect: String = "Select"
    }
}

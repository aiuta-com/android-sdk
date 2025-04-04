package com.aiuta.fashionsdk.tryon.compose.configuration.ui.meta

public interface AiutaStyleMetaData {
    public val mode: AiutaMode

    public class Default : AiutaStyleMetaData {
        override val mode: AiutaMode = AiutaMode.FULL_SCREEN
    }
}

package com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.buttons

public enum class ButtonsMode { DEFAULT, BLURRED }

public interface AiutaImageSelectorUploadsHistoryButtons {
    public val mode: ButtonsMode

    public class Default : AiutaImageSelectorUploadsHistoryButtons {
        override val mode: ButtonsMode = ButtonsMode.DEFAULT
    }
}

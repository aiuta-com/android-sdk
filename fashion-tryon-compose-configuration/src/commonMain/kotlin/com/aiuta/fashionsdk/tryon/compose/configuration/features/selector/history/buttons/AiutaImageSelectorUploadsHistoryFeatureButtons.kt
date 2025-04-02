package com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.buttons

public enum class ButtonsMode { DEFAULT, BLURRED }

public interface AiutaImageSelectorUploadsHistoryFeatureButtons {
    public val mode: ButtonsMode

    public class Default : AiutaImageSelectorUploadsHistoryFeatureButtons {
        override val mode: ButtonsMode = ButtonsMode.DEFAULT
    }
}

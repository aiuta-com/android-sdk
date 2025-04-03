package com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.styles

public enum class ButtonsMode { PRIMARY, BLURRED }

public interface AiutaImageSelectorUploadsHistoryFeatureStyles {
    public val changePhotoButtonStyle: ButtonsMode

    public class Default : AiutaImageSelectorUploadsHistoryFeatureStyles {
        override val changePhotoButtonStyle: ButtonsMode = ButtonsMode.BLURRED
    }
}

package com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.styles

import com.aiuta.fashionsdk.tryon.compose.configuration.features.styles.AiutaButtonsStyle

public interface AiutaImageSelectorUploadsHistoryFeatureStyles {
    public val changePhotoButtonStyle: AiutaButtonsStyle

    public class Default : AiutaImageSelectorUploadsHistoryFeatureStyles {
        override val changePhotoButtonStyle: AiutaButtonsStyle = AiutaButtonsStyle.BLURRED
    }
}

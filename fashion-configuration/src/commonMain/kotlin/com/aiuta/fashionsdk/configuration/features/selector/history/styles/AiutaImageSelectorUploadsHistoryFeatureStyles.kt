package com.aiuta.fashionsdk.configuration.features.selector.history.styles

import com.aiuta.fashionsdk.configuration.features.styles.AiutaButtonsStyle

public interface AiutaImageSelectorUploadsHistoryFeatureStyles {
    public val changePhotoButtonStyle: AiutaButtonsStyle

    public class Default : AiutaImageSelectorUploadsHistoryFeatureStyles {
        override val changePhotoButtonStyle: AiutaButtonsStyle = AiutaButtonsStyle.BLURRED
    }
}

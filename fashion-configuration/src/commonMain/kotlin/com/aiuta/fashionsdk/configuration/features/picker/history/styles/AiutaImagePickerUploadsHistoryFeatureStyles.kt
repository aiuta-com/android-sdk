package com.aiuta.fashionsdk.configuration.features.picker.history.styles

import com.aiuta.fashionsdk.configuration.features.styles.AiutaButtonsStyle

public interface AiutaImagePickerUploadsHistoryFeatureStyles {
    public val changePhotoButtonStyle: AiutaButtonsStyle

    public class Default : AiutaImagePickerUploadsHistoryFeatureStyles {
        override val changePhotoButtonStyle: AiutaButtonsStyle = AiutaButtonsStyle.BLURRED
    }
}

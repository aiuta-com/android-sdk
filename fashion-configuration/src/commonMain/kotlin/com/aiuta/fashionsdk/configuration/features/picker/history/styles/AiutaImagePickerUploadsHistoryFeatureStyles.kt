package com.aiuta.fashionsdk.configuration.features.picker.history.styles

import com.aiuta.fashionsdk.configuration.features.styles.AiutaButtonsStyle

/**
 * Interface defining visual styles for the uploads history feature.
 * 
 * This interface provides style configurations for UI elements
 * in the uploads history interface.
 */
public interface AiutaImagePickerUploadsHistoryFeatureStyles {
    /**
     * Style for the button that allows changing the currently selected photo.
     * Uses the SDK's button style system for consistent appearance.
     */
    public val changePhotoButtonStyle: AiutaButtonsStyle

    /**
     * Default implementation of [AiutaImagePickerUploadsHistoryFeatureStyles].
     * 
     * Provides standard visual styles for the uploads history interface,
     * using a blurred button style for the change photo button.
     */
    public class Default : AiutaImagePickerUploadsHistoryFeatureStyles {
        override val changePhotoButtonStyle: AiutaButtonsStyle = AiutaButtonsStyle.BLURRED
    }
}

package com.aiuta.fashionsdk.configuration.features.picker.history.strings

/**
 * Interface defining text strings used in the uploads history feature.
 *
 * This interface provides strings for titles and buttons displayed
 * in the uploads history interface.
 */
public interface AiutaImagePickerUploadsHistoryFeatureStrings {
    /**
     * Title displayed at the top of the uploads history page.
     */
    public val uploadsHistoryTitle: String

    /**
     * Text for the button that allows adding a new photo or selecting a model.
     * The text varies depending on whether predefined models are available.
     */
    public val uploadsHistoryButtonNewPhoto: String

    /**
     * Text for the button that allows changing the currently selected photo.
     */
    public val uploadsHistoryButtonChangePhoto: String

    /**
     * Default implementation of [AiutaImagePickerUploadsHistoryFeatureStrings].
     *
     * Provides standard English text for the uploads history interface,
     * with dynamic text for the new photo button based on model availability.
     *
     * @param isPredefinedModelAvailable Whether predefined models are available
     */
    public class Default(
        isPredefinedModelAvailable: Boolean,
    ) : AiutaImagePickerUploadsHistoryFeatureStrings {
        override val uploadsHistoryTitle: String = "Previously used photos"
        override val uploadsHistoryButtonNewPhoto: String = if (isPredefinedModelAvailable) {
            "+ New photo or model"
        } else {
            "+ New photo"
        }
        override val uploadsHistoryButtonChangePhoto: String = "Change photo"
    }
}

package com.aiuta.fashionsdk.configuration.features.picker.strings

/**
 * Interface defining text strings used in the image picker feature.
 *
 * This interface provides strings for titles, descriptions, and buttons
 * displayed in the image picker UI.
 */
public interface AiutaImagePickerFeatureStrings {
    /**
     * Title shown when the image picker is empty.
     */
    public val imagePickerTitleEmpty: String

    /**
     * Description shown when the image picker is empty.
     */
    public val imagePickerDescriptionEmpty: String

    /**
     * Text for the button to upload an image.
     */
    public val imagePickerButtonUploadImage: String

    /**
     * Default implementation of [AiutaImagePickerFeatureStrings].
     *
     * Provides standard English text for the image picker interface.
     */
    public class Default : AiutaImagePickerFeatureStrings {
        override val imagePickerTitleEmpty: String = "Upload a photo of you"
        override val imagePickerDescriptionEmpty: String =
            "Select a photo where you are standing straight and clearly visible"
        override val imagePickerButtonUploadImage: String = "Upload a photo"
    }
}

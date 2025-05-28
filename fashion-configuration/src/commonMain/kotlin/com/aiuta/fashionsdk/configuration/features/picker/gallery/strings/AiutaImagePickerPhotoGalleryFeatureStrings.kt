package com.aiuta.fashionsdk.configuration.features.picker.gallery.strings

/**
 * Interface defining text strings used in the photo gallery feature.
 * 
 * This interface provides strings for buttons and messages
 * displayed in the photo gallery interface.
 */
public interface AiutaImagePickerPhotoGalleryFeatureStrings {
    /**
     * Text for the button that opens the device's photo gallery.
     */
    public val galleryButtonSelectPhoto: String

    /**
     * Default implementation of [AiutaImagePickerPhotoGalleryFeatureStrings].
     * 
     * Provides standard English text for the photo gallery interface.
     */
    public class Default : AiutaImagePickerPhotoGalleryFeatureStrings {
        override val galleryButtonSelectPhoto: String = "Choose from library"
    }
}

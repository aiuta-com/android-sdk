package com.aiuta.fashionsdk.configuration.features.tryon.validation.strings

/**
 * Interface for input image validation text strings.
 *
 * This interface defines the text strings used in the input image validation interface,
 * allowing for customization of validation messages and instructions.
 *
 * @property invalidInputImageDescription Message shown when the input image is invalid
 * @property invalidInputImageChangePhotoButton Text for the button to change the invalid photo
 */
public interface AiutaTryOnInputImageValidationFeatureStrings {
    public val invalidInputImageDescription: String
    public val invalidInputImageChangePhotoButton: String

    /**
     * Default implementation of [AiutaTryOnInputImageValidationFeatureStrings].
     *
     * Provides standard English text strings for the input image validation interface.
     */
    public class Default : AiutaTryOnInputImageValidationFeatureStrings {
        override val invalidInputImageDescription: String = "We couldn’t detect anyone in this photo"
        override val invalidInputImageChangePhotoButton: String = "Change photo"
    }
}

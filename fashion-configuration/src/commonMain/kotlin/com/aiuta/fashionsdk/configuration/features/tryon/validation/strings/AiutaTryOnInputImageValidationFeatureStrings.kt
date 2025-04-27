package com.aiuta.fashionsdk.configuration.features.tryon.validation.strings

public interface AiutaTryOnInputImageValidationFeatureStrings {
    public val invalidInputImageDescription: String
    public val invalidInputImageChangePhotoButton: String

    public class Default : AiutaTryOnInputImageValidationFeatureStrings {
        override val invalidInputImageDescription: String = "We couldn’t detect anyone in this photo"
        override val invalidInputImageChangePhotoButton: String = "Change photo"
    }
}

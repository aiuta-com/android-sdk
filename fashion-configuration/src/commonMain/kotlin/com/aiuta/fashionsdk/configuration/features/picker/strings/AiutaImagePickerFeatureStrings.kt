package com.aiuta.fashionsdk.configuration.features.picker.strings

public interface AiutaImagePickerFeatureStrings {
    public val imagePickerTitleEmpty: String
    public val imagePickerDescriptionEmpty: String
    public val imagePickerButtonUploadImage: String

    public class Default : AiutaImagePickerFeatureStrings {
        override val imagePickerTitleEmpty: String = "Upload a photo of you"
        override val imagePickerDescriptionEmpty: String =
            "Select a photo where you are standing straight and clearly visible"
        override val imagePickerButtonUploadImage: String = "Upload a photo"
    }
}

package com.aiuta.fashionsdk.configuration.features.picker.strings

public interface AiutaImagePickerFeatureStrings {
    public val imageSelectorTitleEmpty: String
    public val imageSelectorDescriptionEmpty: String
    public val imageSelectorButtonUploadImage: String

    public class Default : AiutaImagePickerFeatureStrings {
        override val imageSelectorTitleEmpty: String = "Upload a photo of you"
        override val imageSelectorDescriptionEmpty: String =
            "Select a photo where you are standing straight and clearly visible"
        override val imageSelectorButtonUploadImage: String = "Upload a photo"
    }
}

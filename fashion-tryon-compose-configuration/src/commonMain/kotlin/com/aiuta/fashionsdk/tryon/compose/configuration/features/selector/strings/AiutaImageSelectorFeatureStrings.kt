package com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.strings

public interface AiutaImageSelectorFeatureStrings {
    public val imageSelectorTitleEmpty: String
    public val imageSelectorDescriptionEmpty: String
    public val imageSelectorButtonUploadImage: String
    public val imageSelectorButtonChangePhoto: String

    public class Default : AiutaImageSelectorFeatureStrings {
        override val imageSelectorTitleEmpty: String = "Upload a photo of you"
        override val imageSelectorDescriptionEmpty: String = "Select a photo where you are standing straight and clearly visible"
        override val imageSelectorButtonUploadImage: String = "Upload a photo"
        override val imageSelectorButtonChangePhoto: String = "Change photo"
    }
}

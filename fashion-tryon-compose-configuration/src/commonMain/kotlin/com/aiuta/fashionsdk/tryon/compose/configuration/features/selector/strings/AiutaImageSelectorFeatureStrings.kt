package com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.strings

public interface AiutaImageSelectorFeatureStrings {
    public val imageSelectorPageTitle: String
    public val imageSelectorTitleEmpty: String
    public val imageSelectorDescriptionEmpty: String
    public val imageSelectorButtonUploadImage: String
    public val imageSelectorButtonTryOn: String
    public val imageSelectorButtonChangePhoto: String

    public class Default : AiutaImageSelectorFeatureStrings {
        override val imageSelectorPageTitle: String = "Virtual Try-on"
        override val imageSelectorTitleEmpty: String = "Upload a photo of you"
        override val imageSelectorDescriptionEmpty: String = "Select a photo where you are standing straight and clearly visible"
        override val imageSelectorButtonUploadImage: String = "Upload a photo"
        override val imageSelectorButtonTryOn: String = "Try on"
        override val imageSelectorButtonChangePhoto: String = "Change photo"
    }
}

package com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.strings

import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.models.AiutaStringValidationContainer

public interface AiutaImageSelectorFeatureStrings {
    public val imageSelectorTitleEmpty: String
    public val imageSelectorDescriptionEmpty: String
    public val imageSelectorButtonUploadImage: String

    public class Default : AiutaImageSelectorFeatureStrings {
        override val imageSelectorTitleEmpty: String = "Upload a photo of you"
        override val imageSelectorDescriptionEmpty: String = "Select a photo where you are standing straight and clearly visible"
        override val imageSelectorButtonUploadImage: String = "Upload a photo"
    }
}

internal val AiutaImageSelectorFeatureStrings.validationList
    get() = listOf(
        AiutaStringValidationContainer(
            propertyName = "imageSelectorTitleEmpty",
            string = imageSelectorTitleEmpty,
        ),
        AiutaStringValidationContainer(
            propertyName = "imageSelectorDescriptionEmpty",
            string = imageSelectorDescriptionEmpty,
        ),
        AiutaStringValidationContainer(
            propertyName = "imageSelectorButtonUploadImage",
            string = imageSelectorButtonUploadImage,
        ),
    )

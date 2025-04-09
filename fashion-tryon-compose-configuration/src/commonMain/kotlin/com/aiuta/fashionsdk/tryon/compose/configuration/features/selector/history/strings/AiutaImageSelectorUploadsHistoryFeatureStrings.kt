package com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.strings

import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.models.AiutaStringValidationContainer

public interface AiutaImageSelectorUploadsHistoryFeatureStrings {
    public val uploadsHistoryTitle: String
    public val uploadsHistoryButtonNewPhoto: String
    public val uploadsHistoryButtonChangePhoto: String

    public class Default(
        isPredefinedModelAvailable: Boolean,
    ) : AiutaImageSelectorUploadsHistoryFeatureStrings {
        override val uploadsHistoryTitle: String = "Previously used photos"
        override val uploadsHistoryButtonNewPhoto: String = if (isPredefinedModelAvailable) {
            "+ New photo or model"
        } else {
            "+ New photo"
        }
        override val uploadsHistoryButtonChangePhoto: String = "Change photo"
    }
}

internal val AiutaImageSelectorUploadsHistoryFeatureStrings.validationList
    get() = listOf(
        AiutaStringValidationContainer(
            propertyName = "uploadsHistoryTitle",
            string = uploadsHistoryTitle,
        ),
        AiutaStringValidationContainer(
            propertyName = "uploadsHistoryButtonNewPhoto",
            string = uploadsHistoryButtonNewPhoto,
        ),
        AiutaStringValidationContainer(
            propertyName = "uploadsHistoryButtonChangePhoto",
            string = uploadsHistoryButtonChangePhoto,
        ),
    )

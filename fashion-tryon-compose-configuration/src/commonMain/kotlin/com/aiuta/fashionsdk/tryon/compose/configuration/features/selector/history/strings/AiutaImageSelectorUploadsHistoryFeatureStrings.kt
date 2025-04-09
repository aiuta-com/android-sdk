package com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.strings

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

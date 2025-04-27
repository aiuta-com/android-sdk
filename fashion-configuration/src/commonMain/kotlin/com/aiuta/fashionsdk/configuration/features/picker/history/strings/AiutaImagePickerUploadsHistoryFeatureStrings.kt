package com.aiuta.fashionsdk.configuration.features.picker.history.strings

public interface AiutaImagePickerUploadsHistoryFeatureStrings {
    public val uploadsHistoryTitle: String
    public val uploadsHistoryButtonNewPhoto: String
    public val uploadsHistoryButtonChangePhoto: String

    public class Default(
        isPredefinedModelAvailable: Boolean,
    ) : AiutaImagePickerUploadsHistoryFeatureStrings {
        override val uploadsHistoryTitle: String = "Previously used photos"
        override val uploadsHistoryButtonNewPhoto: String = if (isPredefinedModelAvailable) {
            "+ New photo or model"
        } else {
            "+ New photo"
        }
        override val uploadsHistoryButtonChangePhoto: String = "Change photo"
    }
}

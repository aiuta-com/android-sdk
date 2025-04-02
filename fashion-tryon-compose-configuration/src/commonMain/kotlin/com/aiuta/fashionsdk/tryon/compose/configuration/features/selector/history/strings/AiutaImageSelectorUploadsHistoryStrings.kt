package com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.strings

public interface AiutaImageSelectorUploadsHistoryStrings {
    public val uploadsHistoryTitle: String
    public val uploadsHistoryButtonNewPhoto: String

    public class Default(
        isPredefinedModelAvailable: Boolean,
    ) : AiutaImageSelectorUploadsHistoryStrings {
        override val uploadsHistoryTitle: String = "Previously used photos"
        override val uploadsHistoryButtonNewPhoto: String = if (isPredefinedModelAvailable) {
            "+ New photo or model"
        } else {
            "+ New photo"
        }
    }
}

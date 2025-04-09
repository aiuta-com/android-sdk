package com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.loading.strings

public interface AiutaTryOnLoadingPageFeatureStrings {
    public val tryOnLoadingStatusUploadingImage: String
    public val tryOnLoadingStatusScanningBody: String
    public val tryOnLoadingStatusGeneratingOutfit: String

    public class Default : AiutaTryOnLoadingPageFeatureStrings {
        override val tryOnLoadingStatusUploadingImage: String = "Uploading image"
        override val tryOnLoadingStatusScanningBody: String = "Scanning your body"
        override val tryOnLoadingStatusGeneratingOutfit: String = "Generating outfit"
    }
}

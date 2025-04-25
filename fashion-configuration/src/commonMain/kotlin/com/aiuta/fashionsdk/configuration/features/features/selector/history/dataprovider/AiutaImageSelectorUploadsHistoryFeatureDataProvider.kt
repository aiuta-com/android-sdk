package com.aiuta.fashionsdk.configuration.features.features.selector.history.dataprovider

import com.aiuta.fashionsdk.configuration.features.models.images.AiutaHistoryImage
import kotlinx.coroutines.flow.StateFlow

public interface AiutaImageSelectorUploadsHistoryFeatureDataProvider {
    public val uploadedImages: StateFlow<List<AiutaHistoryImage>>
    public val addUploadedImagesAction: suspend (List<AiutaHistoryImage>) -> Unit
    public val deleteUploadedImagesAction: suspend (List<AiutaHistoryImage>) -> Unit
    public val selectUploadedImageAction: suspend (AiutaHistoryImage) -> Unit
}

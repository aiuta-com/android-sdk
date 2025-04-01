package com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.dataprovider

import com.aiuta.fashionsdk.tryon.compose.configuration.dataprovider.AiutaHistoryImage
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

public class AiutaImageSelectorUploadsHistoryDataProvider(
    public val uploadedImages: StateFlow<List<AiutaHistoryImage>>,
    public val isErrorDeletingUploadedImages: SharedFlow<Boolean>,
    public val addUploadedImagesAction: (List<AiutaHistoryImage>) -> Unit,
    public val deleteUploadedImagesAction: (List<AiutaHistoryImage>) -> Unit,
    public val selectUploadedImageAction: (AiutaHistoryImage) -> Unit,
)

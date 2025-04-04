package com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.history.dataprovider

import com.aiuta.fashionsdk.tryon.compose.configuration.models.images.AiutaHistoryImage
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

public interface AiutaTryOnGenerationsHistoryFeatureDataProvider {
    public val generatedImages: StateFlow<List<AiutaHistoryImage>>
    public val addGeneratedImagesAction: (skuId: String, images: List<AiutaHistoryImage>) -> Unit
    public val isErrorDeletingGeneratedImages: SharedFlow<Boolean>
    public val deleteGeneratedImagesAction: (List<AiutaHistoryImage>) -> Unit
}

package com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.history.dataprovider

import com.aiuta.fashionsdk.tryon.compose.configuration.models.images.AiutaHistoryImage
import kotlinx.coroutines.flow.StateFlow

public interface AiutaTryOnGenerationsHistoryFeatureDataProvider {
    public val generatedImages: StateFlow<List<AiutaHistoryImage>>
    public val addGeneratedImagesAction: suspend (productId: String, images: List<AiutaHistoryImage>) -> Unit
    public val deleteGeneratedImagesAction: suspend (List<AiutaHistoryImage>) -> Unit
}

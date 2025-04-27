package com.aiuta.fashionsdk.configuration.features.tryon.history.dataprovider

import com.aiuta.fashionsdk.configuration.features.models.images.AiutaHistoryImage
import kotlinx.coroutines.flow.StateFlow

public interface AiutaTryOnGenerationsHistoryFeatureDataProvider {
    public val generatedImages: StateFlow<List<AiutaHistoryImage>>
    public suspend fun addGeneratedImages(productIds: List<String>, images: List<AiutaHistoryImage>)
    public suspend fun deleteGeneratedImages(images: List<AiutaHistoryImage>)
}

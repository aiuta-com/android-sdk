package com.aiuta.fashionsdk.configuration.features.tryon.history.dataprovider

import com.aiuta.fashionsdk.configuration.features.models.images.AiutaGeneratedImage
import kotlinx.coroutines.flow.StateFlow

public interface AiutaTryOnGenerationsHistoryFeatureDataProviderCustom : AiutaTryOnGenerationsHistoryFeatureDataProvider {
    public val generatedImages: StateFlow<List<AiutaGeneratedImage>>
    public suspend fun addGeneratedImages(productIds: List<String>, images: List<AiutaGeneratedImage>)
    public suspend fun deleteGeneratedImages(images: List<AiutaGeneratedImage>)
}

package com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.history.dataprovider

import com.aiuta.fashionsdk.tryon.compose.configuration.models.images.AiutaAddedGeneratedImages
import com.aiuta.fashionsdk.tryon.compose.configuration.models.images.AiutaHistoryImage
import kotlinx.coroutines.flow.StateFlow

public interface AiutaTryOnGenerationsHistoryFeatureDataProvider {
    public val generatedImages: StateFlow<List<AiutaHistoryImage>>
    public val addGeneratedImagesAction: suspend (AiutaAddedGeneratedImages) -> Unit
    public val deleteGeneratedImagesAction: suspend (List<AiutaHistoryImage>) -> Unit
}

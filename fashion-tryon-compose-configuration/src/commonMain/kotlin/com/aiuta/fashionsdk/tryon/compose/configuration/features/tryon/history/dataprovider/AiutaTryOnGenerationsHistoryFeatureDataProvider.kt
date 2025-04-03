package com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.history.dataprovider

import com.aiuta.fashionsdk.tryon.compose.configuration.dataprovider.AiutaHistoryImage
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

public class AiutaTryOnGenerationsHistoryFeatureDataProvider(
    public val generatedImagesFlow: StateFlow<List<AiutaHistoryImage>>,
    public val addGeneratedImagesAction: (skuId: String, images: List<AiutaHistoryImage>) -> Unit,
    public val isErrorDeletingGeneratedImagesFlow: SharedFlow<Boolean>,
    public val deleteGeneratedImagesAction: (List<AiutaHistoryImage>) -> Unit,
)

package com.aiuta.fashionsdk.tryon.compose.configuration.features.share.dataprovider

import kotlinx.coroutines.flow.StateFlow

public class AiutaShareFeatureDataProvider(
    public val shareText: StateFlow<String>,
    public val requestShareTextAction: (productIds: List<String>) -> Unit,
)

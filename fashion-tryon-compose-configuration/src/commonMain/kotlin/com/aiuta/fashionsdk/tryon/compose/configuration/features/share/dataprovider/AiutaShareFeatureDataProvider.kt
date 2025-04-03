package com.aiuta.fashionsdk.tryon.compose.configuration.features.share.dataprovider

public class AiutaShareFeatureDataProvider(
    public val requestShareTextAction: (productIds: List<String>) -> String,
)

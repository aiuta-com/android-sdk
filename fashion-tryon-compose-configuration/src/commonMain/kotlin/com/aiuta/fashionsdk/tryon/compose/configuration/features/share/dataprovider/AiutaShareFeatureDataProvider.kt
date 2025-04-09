package com.aiuta.fashionsdk.tryon.compose.configuration.features.share.dataprovider

public interface AiutaShareFeatureDataProvider {
    public val requestShareTextAction: (productIds: List<String>) -> String
}

package com.aiuta.fashionsdk.configuration.features.features.share.dataprovider

public interface AiutaShareFeatureDataProvider {
    public val requestShareTextAction: (productIds: List<String>) -> String
}

package com.aiuta.fashionsdk.configuration.features.share.dataprovider

public interface AiutaShareFeatureDataProvider {
    public val requestShareTextAction: (productIds: List<String>) -> String
}

package com.aiuta.fashionsdk.configuration.features.share.dataprovider

public interface AiutaShareFeatureDataProvider {
    public suspend fun getShareText(productIds: List<String>): String
}

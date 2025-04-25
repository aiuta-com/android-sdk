package com.aiuta.fashionsdk.configuration.features.share.dataprovider

public interface AiutaShareFeatureDataProvider {
    public fun getShareText(productIds: List<String>): String
}

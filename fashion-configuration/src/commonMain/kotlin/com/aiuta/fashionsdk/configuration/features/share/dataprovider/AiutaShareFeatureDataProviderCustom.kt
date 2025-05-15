package com.aiuta.fashionsdk.configuration.features.share.dataprovider

public interface AiutaShareFeatureDataProviderCustom {
    public suspend fun getShareText(productIds: List<String>): String
}

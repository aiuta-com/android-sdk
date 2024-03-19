package com.aiuta.fashionsdk.analytic

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.analytic.internal.InternalAiutaAnalyticImpl
import com.aiuta.fashionsdk.analytic.utils.AnalyticConfig
import com.aiuta.fashionsdk.network.createNetworkClient

// TODO Description
public fun defaultInternalAiutaAnalytic(aiuta: Aiuta): InternalAiutaAnalytic {
    return InternalAiutaAnalyticImpl.create(
        context = aiuta.application,
        networkClient =
            createNetworkClient(
                apiKey = aiuta.apiKey,
                backendEndpoint = AnalyticConfig.DEFAULT_ENDPOINT,
            ),
    )
}

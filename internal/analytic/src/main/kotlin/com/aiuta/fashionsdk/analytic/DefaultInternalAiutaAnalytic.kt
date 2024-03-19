package com.aiuta.fashionsdk.analytic

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.analytic.internal.InternalAiutaAnalyticImpl
import com.aiuta.fashionsdk.analytic.utils.AnalyticConfig
import com.aiuta.fashionsdk.network.createNetworkClient

// TODO Description
public val Aiuta.internalAiutaAnalytic: InternalAiutaAnalytic
    get() =
        InternalAiutaAnalyticImpl.create(
            context = application,
            networkClient =
                createNetworkClient(
                    apiKey = apiKey,
                    backendEndpoint = AnalyticConfig.DEFAULT_ENDPOINT,
                ),
        )

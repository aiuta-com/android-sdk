package com.aiuta.fashionsdk.internal.analytic

import android.content.Context
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.internal.analytic.internal.InternalAiutaAnalyticImpl
import com.aiuta.fashionsdk.internal.analytic.utils.AnalyticConfig
import com.aiuta.fashionsdk.network.NetworkClient
import com.aiuta.fashionsdk.network.createNetworkClient

/**
 * Extension for default creation of [InternalAiutaAnalytic].
 *
 * Will cache instance of [InternalAiutaAnalytic]
 */
public val Aiuta.internalAiutaAnalytic: InternalAiutaAnalytic
    get() = InternalAiutaAnalyticFactory.create(this)

public object InternalAiutaAnalyticFactory {
    @Volatile
    private var instance: InternalAiutaAnalytic? = null
    private var cachedApiKey: String? = null
    private var networkClient: NetworkClient? = null

    public fun create(aiuta: Aiuta): InternalAiutaAnalytic {
        validateCacheInstance(newApiKey = aiuta.apiKey)

        return instance ?: synchronized(this) {
            instance ?: buildInternalAiutaAnalyticImpl(
                context = aiuta.application,
            ).also {
                instance = it
                networkClient =
                    createNetworkClient(
                        aiuta = aiuta,
                        backendEndpoint = AnalyticConfig.DEFAULT_ENDPOINT,
                    )
            }
        }
    }

    public fun getNetworkClient(): NetworkClient? {
        return networkClient
    }

    public fun getInternalAiutaAnalytic(): InternalAiutaAnalytic? {
        return instance
    }

    private fun buildInternalAiutaAnalyticImpl(context: Context): InternalAiutaAnalytic {
        return InternalAiutaAnalyticImpl(
            context = context,
        )
    }

    private fun validateCacheInstance(newApiKey: String) {
        // We should remove cache, if we have new instance of api key
        if (newApiKey != cachedApiKey) {
            instance = null
            networkClient = null
            cachedApiKey = newApiKey
        }
    }
}

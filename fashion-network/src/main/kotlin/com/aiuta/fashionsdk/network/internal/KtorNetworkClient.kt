package com.aiuta.fashionsdk.network.internal

import com.aiuta.fashionsdk.network.NetworkClient
import io.ktor.client.HttpClient

internal class KtorNetworkClient(
    override val httpClient: Lazy<HttpClient>,
) : NetworkClient {
    companion object : NetworkClient.Factory {
        @Volatile
        private var instance: NetworkClient? = null
        private var cachedApiKey: String? = null

        override fun create(
            apiKey: String,
            backendEndpoint: String?,
        ): NetworkClient {
            validateCacheInstance(newApiKey = apiKey)

            return instance ?: synchronized(this) {
                instance ?: buildKtorNetworkClient(
                    apiKey = apiKey,
                    backendEndpoint = backendEndpoint,
                ).also {
                    instance = it
                    cachedApiKey = apiKey
                }
            }
        }

        internal fun buildKtorNetworkClient(
            apiKey: String,
            backendEndpoint: String? = null,
        ): NetworkClient {
            return KtorNetworkClient(
                httpClient =
                    KtorHttpClientFactory(
                        apiKey = apiKey,
                        backendEndpoint = backendEndpoint,
                    ).create(),
            )
        }

        private fun validateCacheInstance(newApiKey: String) {
            // We should remove cache, if we have new instance of api key
            if (newApiKey != cachedApiKey) {
                instance = null
                cachedApiKey = newApiKey
            }
        }
    }
}

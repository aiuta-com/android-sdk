package com.aiuta.fashionsdk.network.internal

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.authentication.AuthenticationStrategy
import com.aiuta.fashionsdk.network.NetworkClient
import io.ktor.client.HttpClient

internal class KtorNetworkClient(
    override val httpClient: Lazy<HttpClient>,
) : NetworkClient {
    companion object : NetworkClient.Factory {
        @Volatile
        private var instance: NetworkClient? = null
        private var cachedUniqueId: String? = null

        override fun create(
            aiuta: Aiuta,
            backendEndpoint: String?,
        ): NetworkClient {
            validateCacheInstance(newUniqueId = aiuta.uniqueId)

            return instance ?: synchronized(this) {
                instance ?: buildKtorNetworkClient(
                    aiuta = aiuta,
                    backendEndpoint = backendEndpoint,
                ).also {
                    instance = it
                    cachedUniqueId = aiuta.uniqueId
                }
            }
        }

        internal fun buildKtorNetworkClient(
            aiuta: Aiuta,
            backendEndpoint: String? = null,
        ): NetworkClient {
            return KtorNetworkClient(
                httpClient =
                KtorHttpClientFactory(
                    authenticationStrategy = aiuta.authenticationStrategy,
                    backendEndpoint = backendEndpoint,
                ).create(),
            )
        }

        private fun validateCacheInstance(newUniqueId: String) {
            // We should remove cache, if we have new instance of api key
            if (newUniqueId != cachedUniqueId) {
                instance = null
                cachedUniqueId = newUniqueId
            }
        }
    }
}

package com.aiuta.fashionsdk.network

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.network.internal.KtorNetworkClient

/**
 * Default extension for creating [NetworkClient]
 * Will cache instance of [NetworkClient] for future optimizations
 *
 * @param apiKey The incoming api key received from the Aiuta developer portal.
 * @param backendEndpoint Optional backend endpoint. Otherwise, will use default.
 *
 * @return instance of [NetworkClient]
 */
public fun defaultNetworkClient(
    apiKey: String,
    backendEndpoint: String? = null,
): NetworkClient {
    return KtorNetworkClient.create(
        apiKey = apiKey,
        backendEndpoint = backendEndpoint,
    )
}

/**
 * Default extension for creating [NetworkClient]. Same as [defaultNetworkClient],
 * but use api key from instance of [Aiuta] and take default endpoint
 *
 * @param aiuta Instance of [Aiuta]
 *
 * @return instance of [NetworkClient]
 */
public fun defaultNetworkClient(aiuta: Aiuta): NetworkClient {
    return defaultNetworkClient(
        apiKey = aiuta.apiKey,
    )
}

/**
 * Creating new instance of [NetworkClient]
 * without caching.
 *
 * Be careful, when use this method, because new instance of [NetworkClient] can
 * be too expensive.
 *
 *
 * @param apiKey The incoming api key received from the Aiuta developer portal.
 * @param backendEndpoint Optional backend endpoint. Otherwise, will use default.
 *
 * @return instance of [NetworkClient]
 */
public fun createNetworkClient(
    apiKey: String,
    backendEndpoint: String? = null,
): NetworkClient {
    return KtorNetworkClient.buildKtorNetworkClient(
        apiKey = apiKey,
        backendEndpoint = backendEndpoint,
    )
}

package com.aiuta.fashionsdk.network

import com.aiuta.fashionsdk.Fashion
import com.aiuta.fashionsdk.network.internal.KtorNetworkClient

/**
 * Default extension for creating [NetworkClient]
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
 * but use api key from instance of [Fashion] and take default endpoint
 *
 * @param fashion Instance of [Fashion]
 *
 * @return instance of [NetworkClient]
 */
public fun defaultNetworkClient(fashion: Fashion): NetworkClient {
    return defaultNetworkClient(
        apiKey = fashion.apiKey,
    )
}

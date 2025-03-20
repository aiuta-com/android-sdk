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
@Deprecated(
    message = "This method is not supported yet, please migrate to defaultNetworkClient with Aiuta parameter",
    replaceWith =
        ReplaceWith(
            "defaultNetworkClient(" +
                "aiuta = aiuta," +
                "backendEndpoint = backendEndpoint," +
                ")",
        ),
)
public fun defaultNetworkClient(
    apiKey: String,
    backendEndpoint: String? = null,
): NetworkClient {
    throw IllegalStateException(
        "defaultNetworkClient: This method is not supported, please migrate to new one",
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
public fun defaultNetworkClient(
    aiuta: Aiuta,
    backendEndpoint: String? = null,
): NetworkClient {
    return KtorNetworkClient.create(
        aiuta = aiuta,
        backendEndpoint = backendEndpoint,
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
@Deprecated(
    message = "Use createNetworkClient with ApiKeyAuthenticationStrategy instead",
    replaceWith =
        ReplaceWith(
            expression =
                "createNetworkClient(" +
                    "aiuta = aiuta," +
                    "backendEndpoint = backendEndpoint," +
                    ")",
            imports = arrayOf("com.aiuta.fashionsdk.authentication.ApiKeyAuthenticationStrategy"),
        ),
)
public fun createNetworkClient(
    apiKey: String,
    backendEndpoint: String? = null,
): NetworkClient {
    throw IllegalStateException(
        "createNetworkClient: This method is not supported, please migrate to new one",
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
 * @param aiuta Instance of [Aiuta]
 * @param backendEndpoint Optional backend endpoint. Otherwise, will use default.
 *
 * @return instance of [NetworkClient]
 */
public fun createNetworkClient(
    aiuta: Aiuta,
    backendEndpoint: String? = null,
): NetworkClient {
    return KtorNetworkClient.buildKtorNetworkClient(
        aiuta = aiuta,
        backendEndpoint = backendEndpoint,
    )
}

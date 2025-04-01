package com.aiuta.fashionsdk.network

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.network.internal.KtorNetworkClient

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
    host: String? = null,
    encodedPath: String? = null,
): NetworkClient = KtorNetworkClient.create(
    aiuta = aiuta,
    host = host,
    encodedPath = encodedPath,
)

/**
 * Creating new instance of [NetworkClient]
 * without caching.
 *
 * Be careful, when use this method, because new instance of [NetworkClient] can
 * be too expensive.
 *
 *
 * @param aiuta Instance of [Aiuta]
 * @param host Optional backend host. Otherwise, will use default.
 * @param encodedPath Optional encoded path. Otherwise, will use default.
 *
 * @return instance of [NetworkClient]
 */
public fun createNetworkClient(
    aiuta: Aiuta,
    host: String? = null,
    encodedPath: String? = null,
): NetworkClient = KtorNetworkClient.buildKtorNetworkClient(
    aiuta = aiuta,
    host = host,
    encodedPath = encodedPath,
)

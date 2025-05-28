package com.aiuta.fashionsdk.network

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.network.internal.KtorNetworkClient

/**
 * Creates a default network client with caching enabled.
 * This is the recommended way to create a network client for most use cases.
 *
 * @param aiuta Instance of [Aiuta] containing the API key
 * @param host Optional backend host. If not provided, uses the default endpoint
 * @param encodedPath Optional encoded path. If not provided, uses the default path
 * @return A configured instance of [NetworkClient]
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
 * Creates a new instance of [NetworkClient] without caching.
 * This method should be used with caution as creating new network client instances
 * can be resource-intensive.
 *
 * @param aiuta Instance of [Aiuta] containing the API key
 * @param host Optional backend host. If not provided, uses the default endpoint
 * @param encodedPath Optional encoded path. If not provided, uses the default path
 * @return A new instance of [NetworkClient]
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

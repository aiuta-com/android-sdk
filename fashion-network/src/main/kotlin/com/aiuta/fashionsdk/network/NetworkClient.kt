package com.aiuta.fashionsdk.network

import io.ktor.client.HttpClient

/**
 * Public interface of [NetworkClient] for creating network requests.
 *
 * In the current implementation, all interaction will be done via [httpClient]
 * as part of [io.ktor]
 */
public interface NetworkClient {
    public val httpClient: Lazy<HttpClient>

    public interface Factory {
        /**
         * Factory for [NetworkClient]
         *
         * @param apiKey The incoming api key received from the Aiuta developer portal.
         * @param backendEndpoint Optional backend endpoint. Otherwise, will use default.
         *
         * @return [NetworkClient] that can fetch data by using [HttpClient]
         */
        public fun create(
            apiKey: String,
            backendEndpoint: String? = null,
        ): NetworkClient
    }
}

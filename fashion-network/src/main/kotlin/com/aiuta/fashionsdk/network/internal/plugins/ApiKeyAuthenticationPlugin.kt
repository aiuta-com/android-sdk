package com.aiuta.fashionsdk.network.internal.plugins

import com.aiuta.fashionsdk.authentication.ApiKeyAuthenticationStrategy
import com.aiuta.fashionsdk.authentication.AuthenticationStrategy
import io.ktor.client.request.header
import io.ktor.http.HttpMessageBuilder

private const val API_KEY_AUTH_HEADER = "x-api-key"
private const val ERROR_API_KEY_EMPTY_MESSAGE =
    "Cannot initialize Network client, because api key is empty, please set correct api key"

internal fun HttpMessageBuilder.installApiKeyAuthenticationHeader(
    strategy: ApiKeyAuthenticationStrategy,
) {
    check(strategy.apiKey.isNotEmpty()) { ERROR_API_KEY_EMPTY_MESSAGE }

    header(API_KEY_AUTH_HEADER, strategy.apiKey)
}

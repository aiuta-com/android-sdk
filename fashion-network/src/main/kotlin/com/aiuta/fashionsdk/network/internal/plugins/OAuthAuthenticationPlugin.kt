package com.aiuta.fashionsdk.network.internal.plugins

import com.aiuta.fashionsdk.authentication.OAuthAuthenticationStrategy
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders


internal suspend fun HttpRequestBuilder.installOAuthAuthenticationHeader(
    strategy: OAuthAuthenticationStrategy,
    internalAuthenticationKey: String?,
) {
    bearerAuth(internalAuthenticationKey ?: strategy.getAccessToken())
}


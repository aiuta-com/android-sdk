package com.aiuta.fashionsdk.network.internal.plugins

import com.aiuta.fashionsdk.authentication.ApiKeyAuthenticationStrategy
import com.aiuta.fashionsdk.authentication.AuthenticationStrategy
import com.aiuta.fashionsdk.authentication.OAuthAuthenticationStrategy
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.request.HttpRequestBuilder

internal suspend fun HttpRequestBuilder.installAuthenticationHeader(
    authenticationStrategy: AuthenticationStrategy,
) {
    return when (authenticationStrategy) {
        is ApiKeyAuthenticationStrategy -> installApiKeyAuthenticationHeader(authenticationStrategy)
        is OAuthAuthenticationStrategy -> installOAuthAuthenticationHeader(authenticationStrategy)
    }
}


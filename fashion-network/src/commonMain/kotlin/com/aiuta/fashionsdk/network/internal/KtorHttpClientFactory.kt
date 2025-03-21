package com.aiuta.fashionsdk.network.internal

import com.aiuta.fashionsdk.authentication.ApiKeyAuthenticationStrategy
import com.aiuta.fashionsdk.authentication.AuthenticationStrategy
import com.aiuta.fashionsdk.authentication.JWTAuthenticationStrategy
import com.aiuta.fashionsdk.network.internal.plugins.apiKey
import com.aiuta.fashionsdk.network.internal.plugins.installSubscriptionIdHeader
import com.aiuta.fashionsdk.network.internal.plugins.jwt
import com.aiuta.fashionsdk.network.utils.handleErrors
import com.aiuta.fashionsdk.network.utils.jsonSerializer
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json

internal class KtorHttpClientFactory(
    private val authenticationStrategy: AuthenticationStrategy,
    private val subscriptionId: String,
    private val isLoggingEnabled: Boolean = false, // TODO Migrate from config?
    backendEndpoint: String? = null,
) {
    private val internalBackendEndpoint = backendEndpoint ?: DEFAULT_BACKEND_ENDPOINT

    private fun <T : HttpClientEngineConfig> HttpClientConfig<T>.installSerialization() =
        apply {
            install(ContentNegotiation) {
                json(jsonSerializer)
            }
        }

    private fun <T : HttpClientEngineConfig> HttpClientConfig<T>.installLogging() =
        apply {
            if (isLoggingEnabled) {
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.ALL
                }
            }
        }

    private fun <T : HttpClientEngineConfig> HttpClientConfig<T>.installDefaultRequest() =
        apply {
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                url {
                    protocol = URLProtocol.HTTPS
                    host = internalBackendEndpoint
                }
            }
        }

    private fun <T : HttpClientEngineConfig> HttpClientConfig<T>.installAuthentication(): HttpClientConfig<T> {
        return apply {
            install(Auth) {
                when (authenticationStrategy) {
                    is ApiKeyAuthenticationStrategy ->
                        apiKey {
                            setApiKey(authenticationStrategy.apiKey)
                        }

                    is JWTAuthenticationStrategy ->
                        jwt {
                            loadTokens { authenticationStrategy.getJWT(it) }
                        }
                }
            }
        }
    }

    private fun HttpClient.addDynamicHeader() {
        plugin(HttpSend).intercept { request ->
            if (request.url.host == internalBackendEndpoint) {
                request.installSubscriptionIdHeader(subscriptionId)
            }

            handleErrors { execute(request) }
        }
    }

    public fun create(): Lazy<HttpClient> {
        return lazy {
            HttpClient {
                installAuthentication()
                installSerialization()
                installLogging()
                installDefaultRequest()
            }.apply { addDynamicHeader() }
        }
    }

    private companion object {
        const val DEFAULT_BACKEND_ENDPOINT = "api.aiuta.com/digital-try-on/v1"
    }
}

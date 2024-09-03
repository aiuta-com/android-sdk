package com.aiuta.fashionsdk.network.internal

import android.util.Log
import com.aiuta.fashionsdk.authentication.ApiKeyAuthenticationStrategy
import com.aiuta.fashionsdk.authentication.AuthenticationStrategy
import com.aiuta.fashionsdk.authentication.OAuthAuthenticationStrategy
import com.aiuta.fashionsdk.network.BuildConfig
import com.aiuta.fashionsdk.network.internal.plugins.installApiKeyAuthenticationHeader
import com.aiuta.fashionsdk.network.internal.plugins.installAuthenticationHeader
import com.aiuta.fashionsdk.network.internal.plugins.installOAuthAuthenticationHeader
import com.aiuta.fashionsdk.network.utils.handleErrors
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal class KtorHttpClientFactory(
    private val authenticationStrategy: AuthenticationStrategy,
    backendEndpoint: String? = null,
) {
    private val internalBackendEndpoint = backendEndpoint ?: DEFAULT_BACKEND_ENDPOINT
    private val internalAuthenticationKey: String? = null

    private fun <T : HttpClientEngineConfig> HttpClientConfig<T>.installSerialization() =
        apply {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        ignoreUnknownKeys = true
                    },
                )
            }
        }

    private fun <T : HttpClientEngineConfig> HttpClientConfig<T>.installLogging() =
        apply {
            if (BuildConfig.DEBUG) {
                install(Logging) {
                    logger =
                        object : Logger {
                            val TAG = "HTTP_CLIENT_TAG"

                            override fun log(message: String) {
                                Log.d(TAG, "ktorNetworkClient: $message")
                            }
                        }
                    level = LogLevel.ALL
                }
            }
        }

    private fun <T : HttpClientEngineConfig> HttpClientConfig<T>.installRetryPolicy() =
        apply {
            install(HttpRequestRetry) {

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

    private fun HttpClient.addDynamicHeader() {
        plugin(HttpSend).intercept { request ->
            when (authenticationStrategy) {
                is ApiKeyAuthenticationStrategy -> request.installApiKeyAuthenticationHeader(
                    strategy = authenticationStrategy,
                )

                is OAuthAuthenticationStrategy -> request.installOAuthAuthenticationHeader(
                    strategy = authenticationStrategy,
                )
            }
            handleErrors { execute(request) }
        }

    }


    public fun create(): Lazy<HttpClient> {
        return lazy {
            HttpClient {
                installSerialization()
                installLogging()
                installDefaultRequest()
                installRetryPolicy()
            }.apply { addDynamicHeader() }
        }
    }

    private companion object {
        const val DEFAULT_BACKEND_ENDPOINT = "api.aiuta.com/digital-try-on/v1"
    }
}

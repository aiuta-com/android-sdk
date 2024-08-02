package com.aiuta.fashionsdk.network.internal

import android.util.Log
import com.aiuta.fashionsdk.network.BuildConfig
import com.aiuta.fashionsdk.network.utils.handleErrors
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpSend
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
    private val apiKey: String,
    backendEndpoint: String? = null,
) {
    private val internalBackendEndpoint = backendEndpoint ?: DEFAULT_BACKEND_ENDPOINT

    init {
        check(apiKey.isNotEmpty()) { ERROR_API_KEY_EMPTY_MESSAGE }
    }

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
            if (request.url.host == internalBackendEndpoint) {
                request.header(
                    key = HEADER_API_KEY,
                    value = apiKey,
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
            }.apply { addDynamicHeader() }
        }
    }

    private companion object {
        const val DEFAULT_BACKEND_ENDPOINT = "api.aiuta.com/digital-try-on/v1"
        const val HEADER_API_KEY = "x-api-key"

        const val ERROR_API_KEY_EMPTY_MESSAGE = """
                Cannot initialize Network client, because api key is empty, please set correct api key
            """
    }
}

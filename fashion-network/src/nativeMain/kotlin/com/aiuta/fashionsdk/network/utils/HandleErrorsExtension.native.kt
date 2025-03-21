package com.aiuta.fashionsdk.network.utils

import com.aiuta.fashionsdk.network.exceptions.FashionNetworkIsDisconnected
import io.ktor.client.call.HttpClientCall
import io.ktor.client.engine.darwin.DarwinHttpRequestException
import io.ktor.http.HttpStatusCode

internal actual suspend inline fun handleErrors(
    getHttpClientCall: () -> HttpClientCall,
): HttpClientCall {
    return try {
        val httpClientCall = getHttpClientCall()

        // Check for auth
        if (httpClientCall.response.status == HttpStatusCode.Unauthorized) return httpClientCall

        val fashionIOException = getFashionIOException(httpClientCall.response)
        if (fashionIOException != null) {
            throw fashionIOException
        }
        httpClientCall
    } catch (e: DarwinHttpRequestException) {
        throw FashionNetworkIsDisconnected(e)
    }
}

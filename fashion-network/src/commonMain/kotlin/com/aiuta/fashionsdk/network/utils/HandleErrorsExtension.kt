package com.aiuta.fashionsdk.network.utils

import com.aiuta.fashionsdk.network.exceptions.FashionIOException
import com.aiuta.fashionsdk.network.internal.models.FashionErrorBody
import io.ktor.client.call.HttpClientCall
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

private const val REQUEST_ID_HEADER = "x-cloud-trace-context"
private const val DATE_HEADER = "date"

internal suspend inline fun handleErrors(
    getHttpClientCall: () -> HttpClientCall,
): HttpClientCall {
    val httpClientCall = getHttpClientCall()

    // Check for auth
    if (httpClientCall.response.status == HttpStatusCode.Unauthorized) return httpClientCall

    val fashionIOException = getFashionIOException(httpClientCall.response)
    if (fashionIOException != null) {
        throw fashionIOException
    }
    return httpClientCall
}

internal suspend fun getFashionIOException(response: HttpResponse): FashionIOException? {
    if (response.status != HttpStatusCode.OK) {
        val requestId = response.headers[REQUEST_ID_HEADER].orEmpty()
        val date = response.headers[DATE_HEADER].orEmpty()

        return FashionIOException(
            errorCode = response.status.value,
            errorMessages = response.getErrorMessages(),
            requestId = requestId,
            date = date,
        )
    }
    return null
}

private suspend fun HttpResponse.getErrorMessages(): List<String> = try {
    body<FashionErrorBody>().detail.map { it.msg }
} catch (e: Exception) {
    emptyList()
}

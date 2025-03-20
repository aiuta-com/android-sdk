package com.aiuta.fashionsdk.network.utils

import com.aiuta.fashionsdk.network.exceptions.FashionIOException
import com.aiuta.fashionsdk.network.internal.models.FashionErrorBody
import io.ktor.client.call.HttpClientCall
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

private const val REQUEST_ID_HEADER = "x-cloud-trace-context"
private const val DATE_HEADER = "date"

internal expect suspend inline fun handleErrors(
    getHttpClientCall: () -> HttpClientCall,
): HttpClientCall

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

private suspend fun HttpResponse.getErrorMessages(): List<String> {
    return try {
        body<FashionErrorBody>().detail.map { it.msg }
    } catch (e: Exception) {
        emptyList()
    }
}

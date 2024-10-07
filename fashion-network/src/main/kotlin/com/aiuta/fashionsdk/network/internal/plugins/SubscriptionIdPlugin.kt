package com.aiuta.fashionsdk.network.internal.plugins

import io.ktor.client.request.headers
import io.ktor.http.HttpMessageBuilder

private const val HEADER_SUBSCRIPTION_ID = "x-user-id"

internal fun HttpMessageBuilder.installSubscriptionIdHeader(subscriptionId: String) {
    headers {
        if (contains(HEADER_SUBSCRIPTION_ID)) {
            remove(HEADER_SUBSCRIPTION_ID)
        }
        append(HEADER_SUBSCRIPTION_ID, subscriptionId)
    }
}

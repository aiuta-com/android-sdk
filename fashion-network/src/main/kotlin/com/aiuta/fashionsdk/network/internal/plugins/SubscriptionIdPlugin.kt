package com.aiuta.fashionsdk.network.internal.plugins

import io.ktor.client.request.header
import io.ktor.http.HttpMessageBuilder

private const val HEADER_SUBSCRIPTION_ID = "x-user-id"

internal fun HttpMessageBuilder.installSubscriptionIdHeader(subscriptionId: String) {
    header(HEADER_SUBSCRIPTION_ID, subscriptionId)
}

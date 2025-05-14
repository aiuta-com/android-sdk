package com.aiuta.fashionsdk.network.internal.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class FashionErrorBody(
    @SerialName("detail")
    val detail: List<Detail>? = null,
) {
    @Serializable
    data class Detail(
        @SerialName("msg")
        val msg: String,
        @SerialName("type")
        val type: String,
    )
}

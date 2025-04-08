package com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal enum class ProductOperationStatus {
    @SerialName("CREATED")
    CREATED,

    @SerialName("IN_PROGRESS")
    IN_PROGRESS,

    @SerialName("SUCCESS")
    SUCCESS,

    @SerialName("ABORTED")
    ABORTED,

    @SerialName("FAILED")
    FAILED,
}

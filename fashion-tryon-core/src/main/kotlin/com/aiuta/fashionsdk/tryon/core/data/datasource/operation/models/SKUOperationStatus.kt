package com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal enum class SKUOperationStatus {
    @SerialName("CREATED")
    CREATED,

    @SerialName("IN_PROGRESS")
    IN_PROGRESS,

    @SerialName("SUCCESS")
    SUCCESS,

    @SerialName("FAILED")
    FAILED,
}

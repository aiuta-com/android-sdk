package com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SKUOperation(
    @SerialName("error")
    val error: String? = null,
    @SerialName("generated_images")
    val generatedImages: List<GeneratedImage>,
    @SerialName("id")
    val id: String,
    @SerialName("status")
    val status: SKUOperationStatus,
)

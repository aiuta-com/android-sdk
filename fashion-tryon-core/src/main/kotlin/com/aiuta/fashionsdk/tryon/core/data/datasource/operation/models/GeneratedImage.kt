package com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GeneratedImage(
    @SerialName("image_url")
    val imageUrl: String,
)

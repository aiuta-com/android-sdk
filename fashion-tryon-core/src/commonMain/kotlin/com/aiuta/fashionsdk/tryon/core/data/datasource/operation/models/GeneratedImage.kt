package com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models

import com.aiuta.fashionsdk.tryon.core.data.datasource.image.models.AiutaFileType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GeneratedImage(
    @SerialName("id")
    val id: String,
    @SerialName("url")
    val url: String,
    @SerialName("owner_type")
    val type: AiutaFileType,
)

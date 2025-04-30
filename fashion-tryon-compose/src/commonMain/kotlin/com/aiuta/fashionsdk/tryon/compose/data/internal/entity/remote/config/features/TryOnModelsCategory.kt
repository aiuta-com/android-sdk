package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features

import com.aiuta.fashionsdk.tryon.core.data.datasource.image.models.AiutaFileType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class TryOnModelsCategory(
    @SerialName("category")
    val category: String,
    @SerialName("models")
    val models: List<TryOnModel>,
) {
    @Serializable
    internal class TryOnModel(
        @SerialName("id")
        val id: String,
        @SerialName("url")
        val url: String,
        @SerialName("type")
        val type: AiutaFileType,
    )
}

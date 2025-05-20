package com.aiuta.fashionsdk.tryon.core.data.datasource.image.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class AiutaFileType {
    @SerialName("user")
    USER,

    @SerialName("aiuta")
    AIUTA,
}

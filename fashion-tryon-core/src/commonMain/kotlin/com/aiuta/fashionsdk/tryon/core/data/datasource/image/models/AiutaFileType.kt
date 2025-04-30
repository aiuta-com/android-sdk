package com.aiuta.fashionsdk.tryon.core.data.datasource.image.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class AiutaFileType {
    @SerialName("uploaded")
    UPLOADED,

    @SerialName("generated")
    GENERATED,

    @SerialName("input_model")
    INPUT_MODEL,

    @SerialName("output_model")
    OUTPUT_MODEL,
}

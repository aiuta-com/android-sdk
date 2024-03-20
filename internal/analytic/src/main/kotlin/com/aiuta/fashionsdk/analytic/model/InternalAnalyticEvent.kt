package com.aiuta.fashionsdk.analytic.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class InternalAnalyticEvent(
    @SerialName("name")
    public val name: String,
    @SerialName("parameters")
    public val params: Map<String, String?>,
)

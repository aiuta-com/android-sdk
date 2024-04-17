package com.aiuta.fashionsdk.internal.analytic.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class CompletedInternalAnalyticEvent(
    @SerialName("name")
    public val name: String,
    @SerialName("parameters")
    public val params: Map<String, String?>,
)

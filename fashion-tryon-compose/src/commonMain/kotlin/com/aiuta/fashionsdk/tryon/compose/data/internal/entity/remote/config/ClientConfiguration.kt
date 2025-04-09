package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config

import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features.TryOnModelsCategory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class ClientConfiguration(
    @SerialName("predefined_try_on_models")
    val predefinedTryOnModels: List<TryOnModelsCategory>? = null,
)

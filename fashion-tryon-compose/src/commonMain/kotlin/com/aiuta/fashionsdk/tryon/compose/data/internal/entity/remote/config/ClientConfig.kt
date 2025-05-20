package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features.TryOnModelsCategory

@Immutable
internal class ClientConfig(
    val etag: String? = null,
    val predefinedTryOnModels: List<TryOnModelsCategory>? = null,
)

package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.config

import com.aiuta.fashionsdk.tryon.compose.data.internal.database.Tryon_model
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features.TryOnModelsCategory

internal fun Tryon_model.toDTO(): TryOnModelsCategory.TryOnModel = TryOnModelsCategory.TryOnModel(
    id = id,
    url = url,
    type = type,
)

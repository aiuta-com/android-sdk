package com.aiuta.fashionsdk.tryon.compose.domain.models

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.BackendImageEntity

@Immutable
internal data class GeneratedImage(
    val id: Long,
    val imageUrl: String,
)

internal fun BackendImageEntity.toUiModel(): GeneratedImage {
    return GeneratedImage(
        id = id,
        imageUrl = imageUrl,
    )
}

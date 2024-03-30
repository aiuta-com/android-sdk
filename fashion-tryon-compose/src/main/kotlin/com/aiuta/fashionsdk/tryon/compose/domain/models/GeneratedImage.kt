package com.aiuta.fashionsdk.tryon.compose.domain.models

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.GeneratedImageEntity

@Immutable
internal class GeneratedImage(
    val id: Long,
    val imageUrl: String,
)

internal fun GeneratedImageEntity.toUiModel(): GeneratedImage {
    return GeneratedImage(
        id = id,
        imageUrl = imageUrl,
    )
}

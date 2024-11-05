package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.images.GeneratedImageEntity

@Immutable
internal data class GeneratedImage(
    val id: Long,
    val imageUrl: String,
)

internal fun GeneratedImageEntity.toUiModel(): GeneratedImage {
    return GeneratedImage(
        id = id,
        imageUrl = imageUrl,
    )
}

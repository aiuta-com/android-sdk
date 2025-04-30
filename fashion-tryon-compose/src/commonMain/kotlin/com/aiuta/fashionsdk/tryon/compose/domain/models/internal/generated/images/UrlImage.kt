package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.core.data.datasource.image.models.AiutaFileType

@Immutable
internal data class UrlImage(
    val imageId: String,
    val imageUrl: String,
    val imageType: AiutaFileType,
)

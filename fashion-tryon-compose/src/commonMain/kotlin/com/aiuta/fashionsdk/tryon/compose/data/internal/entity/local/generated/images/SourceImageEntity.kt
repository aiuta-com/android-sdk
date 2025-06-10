package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.images

import com.aiuta.fashionsdk.tryon.compose.data.internal.database.Source_image
import com.aiuta.fashionsdk.tryon.core.data.datasource.image.models.AiutaFileType

internal class SourceImageEntity(
    val id: String,
    val operationId: String,
    val imageUrl: String,
    val imageType: AiutaFileType,
)

internal fun Source_image.toEntity(): SourceImageEntity = SourceImageEntity(
    id = id,
    operationId = operation_id,
    imageUrl = imageUrl,
    imageType = imageType,
)

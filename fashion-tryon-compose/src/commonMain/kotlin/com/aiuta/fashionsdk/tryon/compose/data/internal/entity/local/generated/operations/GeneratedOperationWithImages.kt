package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.operations

import androidx.room.Embedded
import androidx.room.Relation
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.images.SourceImageEntity

internal data class GeneratedOperationWithImages(
    @Embedded val operation: GeneratedOperationEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "operationId",
    )
    val sourceImages: List<SourceImageEntity>,
)

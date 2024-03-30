package com.aiuta.fashionsdk.tryon.compose.data.internal.entity

import androidx.room.Embedded
import androidx.room.Relation

internal data class GeneratedOperationWithImages(
    @Embedded val operation: GeneratedOperationEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "operationId",
    )
    val sourceImages: List<SourceImageEntity>,
)

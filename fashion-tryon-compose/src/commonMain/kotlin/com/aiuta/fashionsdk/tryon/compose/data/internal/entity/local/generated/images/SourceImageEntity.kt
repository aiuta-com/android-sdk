package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.images

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "source_images")
internal class SourceImageEntity(
    @PrimaryKey
    val id: String,
    val operationId: String,
    val imageUrl: String,
)

package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.images

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "generated_images")
internal class GeneratedImageEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val imageUrl: String,
)

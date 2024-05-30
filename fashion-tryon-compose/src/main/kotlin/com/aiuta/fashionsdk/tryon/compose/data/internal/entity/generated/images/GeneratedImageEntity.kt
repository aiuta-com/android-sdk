package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.generated.images

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "generated_images")
internal class GeneratedImageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val imageUrl: String,
)

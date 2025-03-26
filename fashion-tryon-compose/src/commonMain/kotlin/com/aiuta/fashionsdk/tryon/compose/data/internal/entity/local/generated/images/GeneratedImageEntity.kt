package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.images

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Entity(tableName = "generated_images")
internal class GeneratedImageEntity(
    @PrimaryKey
    val id: String = Uuid.random().toString(),
    val imageUrl: String,
)

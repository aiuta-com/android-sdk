package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.images

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.converters.ListStringsConverter
import com.aiuta.fashionsdk.tryon.core.data.datasource.image.models.AiutaFileType
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Entity(tableName = "generated_images")
internal class GeneratedImageEntity(
    @PrimaryKey
    val id: String = Uuid.random().toString(),
    val imageUrl: String,
    val type: AiutaFileType,
    @TypeConverters(ListStringsConverter::class)
    val productIds: List<String> = emptyList(),
)

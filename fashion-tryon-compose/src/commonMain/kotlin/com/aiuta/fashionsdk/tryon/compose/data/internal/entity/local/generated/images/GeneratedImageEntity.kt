package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.images

import com.aiuta.fashionsdk.tryon.core.data.datasource.image.models.AiutaFileType
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
internal class GeneratedImageEntity(
    val id: String = Uuid.random().toString(),
    val imageUrl: String,
    val type: AiutaFileType,
    val productIds: List<String> = emptyList(),
)

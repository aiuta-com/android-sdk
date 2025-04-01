package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.configuration.dataprovider.AiutaHistoryImage
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.images.GeneratedImageEntity
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGeneratedImage

@Immutable
internal data class GeneratedImageUIModel(
    val id: String,
    val imageUrl: String,
)

internal fun GeneratedImageEntity.toUiModel(): GeneratedImageUIModel = GeneratedImageUIModel(
    id = id,
    imageUrl = imageUrl,
)

internal fun GeneratedImageUIModel.toEntity(): GeneratedImageEntity = GeneratedImageEntity(
    id = id,
    imageUrl = imageUrl,
)

internal fun GeneratedImageUIModel.toSessionUiModel(): SessionImageUIModel = SessionImageUIModel(
    id = id,
    imageUrl = imageUrl,
)

internal fun SKUGeneratedImage.toUiModel(): GeneratedImageUIModel = GeneratedImageUIModel(
    id = id,
    imageUrl = url,
)

// History image
internal fun GeneratedImageUIModel.toPublic(): AiutaHistoryImage = AiutaHistoryImage(
    id = id,
    url = imageUrl,
)

internal fun AiutaHistoryImage.toImageUiModel(): GeneratedImageUIModel = GeneratedImageUIModel(
    id = id,
    imageUrl = url,
)

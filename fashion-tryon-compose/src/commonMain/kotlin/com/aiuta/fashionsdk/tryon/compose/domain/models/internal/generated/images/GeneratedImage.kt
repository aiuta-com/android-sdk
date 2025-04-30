package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.features.models.images.AiutaHistoryImage
import com.aiuta.fashionsdk.configuration.features.models.images.AiutaHistoryImageType
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.images.GeneratedImageEntity
import com.aiuta.fashionsdk.tryon.core.data.datasource.image.models.AiutaFileType
import com.aiuta.fashionsdk.tryon.core.domain.models.ProductGeneratedImage

@Immutable
internal data class GeneratedImageUIModel(
    val id: String,
    val imageUrl: String,
    val type: AiutaFileType,
)

internal fun GeneratedImageEntity.toUiModel(): GeneratedImageUIModel = GeneratedImageUIModel(
    id = id,
    imageUrl = imageUrl,
    type = type,
)

internal fun GeneratedImageUIModel.toEntity(): GeneratedImageEntity = GeneratedImageEntity(
    id = id,
    imageUrl = imageUrl,
    type = type,
)

internal fun GeneratedImageUIModel.toSessionUiModel(): SessionImageUIModel = SessionImageUIModel(
    id = id,
    imageUrl = imageUrl,
)

internal fun ProductGeneratedImage.toUiModel(): GeneratedImageUIModel = GeneratedImageUIModel(
    id = id,
    imageUrl = url,
    type = type,
)

// History image
internal fun GeneratedImageUIModel.toPublic(): AiutaHistoryImage = AiutaHistoryImage(
    id = id,
    url = imageUrl,
    type = type.toPublicHistory(),
)

internal fun AiutaHistoryImage.toImageUiModel(): GeneratedImageUIModel = GeneratedImageUIModel(
    id = id,
    imageUrl = url,
    type = type.toPublicCore(),
)

// Type
internal fun AiutaHistoryImageType.toPublicCore(): AiutaFileType = when (this) {
    AiutaHistoryImageType.UPLOADED -> AiutaFileType.UPLOADED
    AiutaHistoryImageType.GENERATED -> AiutaFileType.GENERATED
    AiutaHistoryImageType.INPUT_MODEL -> AiutaFileType.INPUT_MODEL
    AiutaHistoryImageType.OUTPUT_MODEL -> AiutaFileType.OUTPUT_MODEL
}

internal fun AiutaFileType.toPublicHistory(): AiutaHistoryImageType = when (this) {
    AiutaFileType.UPLOADED -> AiutaHistoryImageType.UPLOADED
    AiutaFileType.GENERATED -> AiutaHistoryImageType.GENERATED
    AiutaFileType.INPUT_MODEL -> AiutaHistoryImageType.INPUT_MODEL
    AiutaFileType.OUTPUT_MODEL -> AiutaHistoryImageType.OUTPUT_MODEL
}

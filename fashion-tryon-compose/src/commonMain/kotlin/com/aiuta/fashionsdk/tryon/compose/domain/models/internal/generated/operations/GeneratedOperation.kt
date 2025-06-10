package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.features.models.images.AiutaInputImage
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.operations.GeneratedOperationWithImagesEntity
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.UrlImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.toPublicCore
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.toPublicHistory

@Immutable
internal data class GeneratedOperationUIModel(
    val operationId: String,
    val urlImages: List<UrlImage>,
) {
    val sourceImageUrls by lazy {
        urlImages.map { it.imageUrl }
    }
}

internal fun GeneratedOperationWithImagesEntity.toUiModel(): GeneratedOperationUIModel = GeneratedOperationUIModel(
    operationId = operationId,
    urlImages = sourceImages.map {
        UrlImage(
            imageId = it.id,
            imageUrl = it.imageUrl,
            imageType = it.imageType,
        )
    },
)

// History image
internal fun AiutaInputImage.toOperationUiModel(): GeneratedOperationUIModel = GeneratedOperationUIModel(
    operationId = id,
    urlImages = listOf(UrlImage(imageId = id, imageUrl = url, imageType = type.toPublicCore())),
)

internal fun GeneratedOperationUIModel.toPublic(): List<AiutaInputImage> = urlImages.map { image ->
    AiutaInputImage(
        id = image.imageId,
        url = image.imageUrl,
        type = image.imageType.toPublicHistory(),
    )
}

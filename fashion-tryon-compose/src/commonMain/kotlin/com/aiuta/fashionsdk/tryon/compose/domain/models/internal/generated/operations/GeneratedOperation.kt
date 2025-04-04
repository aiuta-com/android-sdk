package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.configuration.models.images.AiutaHistoryImage
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.operations.GeneratedOperationWithImages
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.UrlImage

@Immutable
internal data class GeneratedOperationUIModel(
    val operationId: String,
    val urlImages: List<UrlImage>,
) {
    val sourceImageUrls by lazy {
        urlImages.map { it.imageUrl }
    }
}

internal fun GeneratedOperationWithImages.toUiModel(): GeneratedOperationUIModel = GeneratedOperationUIModel(
    operationId = operation.id,
    urlImages =
    sourceImages.map {
        UrlImage(
            imageId = it.id,
            imageUrl = it.imageUrl,
        )
    },
)

// History image
internal fun AiutaHistoryImage.toOperationUiModel(): GeneratedOperationUIModel = GeneratedOperationUIModel(
    operationId = id,
    urlImages = listOf(UrlImage(imageId = id, imageUrl = url)),
)

internal fun GeneratedOperationUIModel.toPublic(): List<AiutaHistoryImage> = urlImages.map { image ->
    AiutaHistoryImage(
        id = image.imageId,
        url = image.imageUrl,
    )
}

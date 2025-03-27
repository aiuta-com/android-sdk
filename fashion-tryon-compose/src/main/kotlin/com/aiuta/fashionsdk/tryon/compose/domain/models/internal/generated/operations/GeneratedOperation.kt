package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations

import androidx.compose.runtime.Immutable
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

internal fun GeneratedOperationWithImages.toUiModel(): GeneratedOperationUIModel {
    return GeneratedOperationUIModel(
        operationId = operation.id,
        urlImages =
            sourceImages.map {
                UrlImage(
                    imageId = it.id,
                    imageUrl = it.imageUrl,
                )
            },
    )
}

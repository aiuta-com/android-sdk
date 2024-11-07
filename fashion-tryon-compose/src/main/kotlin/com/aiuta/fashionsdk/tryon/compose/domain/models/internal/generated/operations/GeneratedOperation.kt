package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.operations.GeneratedOperationWithImages
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.SourceImage

@Immutable
internal data class GeneratedOperationUIModel(
    val operationId: String,
    val sourceImages: List<SourceImage>,
) {
    val sourceImageUrls by lazy {
        sourceImages.map { it.imageUrl }
    }
}

internal fun GeneratedOperationWithImages.toUiModel(): GeneratedOperationUIModel {
    return GeneratedOperationUIModel(
        operationId = operation.id,
        sourceImages =
            sourceImages.map {
                SourceImage(
                    imageId = it.id,
                    imageUrl = it.imageUrl,
                )
            },
    )
}

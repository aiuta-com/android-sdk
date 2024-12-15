package com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.dataprovider

import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImageUIModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.SourceImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperationUIModel

public class AiutaHistoryImage(
    public val id: String,
    public val url: String,
)

internal fun AiutaHistoryImage.toImageUiModel(): GeneratedImageUIModel {
    return GeneratedImageUIModel(
        id = id,
        imageUrl = url,
    )
}

internal fun AiutaHistoryImage.toOperationUiModel(): GeneratedOperationUIModel {
    return GeneratedOperationUIModel(
        operationId = id,
        sourceImages = listOf(SourceImage(imageId = id, imageUrl = url)),
    )
}

internal fun GeneratedImageUIModel.toPublic(): AiutaHistoryImage {
    return AiutaHistoryImage(
        id = id,
        url = imageUrl,
    )
}

internal fun GeneratedOperationUIModel.toPublic(): List<AiutaHistoryImage> {
    return sourceImages.map { image ->
        AiutaHistoryImage(
            id = image.imageId,
            url = image.imageUrl,
        )
    }
}

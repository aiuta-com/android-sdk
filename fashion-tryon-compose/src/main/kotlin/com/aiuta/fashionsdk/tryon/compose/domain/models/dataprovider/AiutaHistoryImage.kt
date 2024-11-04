package com.aiuta.fashionsdk.tryon.compose.domain.models.dataprovider

import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.SourceImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperation

public class AiutaUploadedImage(
    public val id: String,
    public val url: String,
)

public class AiutaGeneratedImage(
    public val url: String,
)

internal fun AiutaUploadedImage.toGeneratedOperation(): GeneratedOperation {
    return GeneratedOperation(
        operationId = id.hashCode().toLong(),
        sourceImages = listOf(SourceImage(imageId = id, imageUrl = url)),
    )
}

internal fun AiutaGeneratedImage.toGeneratedImage(): GeneratedImage {
    return GeneratedImage(
        id = url.hashCode().toLong(),
        imageUrl = url,
    )
}

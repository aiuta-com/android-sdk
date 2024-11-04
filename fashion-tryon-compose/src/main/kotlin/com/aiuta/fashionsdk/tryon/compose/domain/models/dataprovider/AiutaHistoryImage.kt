package com.aiuta.fashionsdk.tryon.compose.domain.models.dataprovider

import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImage

public class AiutaUploadedImage(
    public val id: String,
    public val url: String,
)

public class AiutaGeneratedImage(
    public val url: String,
)

internal fun AiutaGeneratedImage.toGeneratedImage(): GeneratedImage {
    return GeneratedImage(
        id = url.hashCode().toLong(),
        imageUrl = url,
    )
}

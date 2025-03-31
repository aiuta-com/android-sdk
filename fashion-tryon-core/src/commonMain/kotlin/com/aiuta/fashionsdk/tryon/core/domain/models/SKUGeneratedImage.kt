package com.aiuta.fashionsdk.tryon.core.domain.models

import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models.GeneratedImage

/**
 * Model of generated image after ping operations
 */
public class SKUGeneratedImage(
    public val id: String,
    public val url: String,
)

internal fun GeneratedImage.toPublic(): SKUGeneratedImage {
    return SKUGeneratedImage(
        id = id,
        url = url,
    )
}

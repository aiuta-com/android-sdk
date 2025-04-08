package com.aiuta.fashionsdk.tryon.core.domain.models

import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models.GeneratedImage

/**
 * Model of generated image after ping operations
 */
public class ProductGeneratedImage(
    public val id: String,
    public val url: String,
)

internal fun GeneratedImage.toPublic(): ProductGeneratedImage = ProductGeneratedImage(
    id = id,
    url = url,
)

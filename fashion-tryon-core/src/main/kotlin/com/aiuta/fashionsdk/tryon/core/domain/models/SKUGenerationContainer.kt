package com.aiuta.fashionsdk.tryon.core.domain.models

import android.net.Uri
import com.aiuta.fashionsdk.tryon.core.AiutaTryOn

/**
 * Base container for starting sku generation flow
 *
 * @see AiutaTryOn.startSKUGeneration
 */
public sealed interface SKUGenerationContainer {
    public val skuId: String
    public val skuCatalogName: String?
}

/**
 * Container for starting sku generation flow with file uri,
 * provided from system
 *
 * @see AiutaTryOn.startSKUGeneration
 * @see SKUGenerationContainer
 */
public class SKUGenerationUriContainer(
    public val fileUri: Uri,
    override val skuId: String,
    override val skuCatalogName: String?,
) : SKUGenerationContainer

/**
 * Container for starting sku generation flow with already
 * uploaded image on Aiuta backend
 *
 * @see AiutaTryOn.startSKUGeneration
 * @see SKUGenerationContainer
 */
public class SKUGenerationUrlContainer(
    public val fileId: String,
    public val fileUrl: String,
    override val skuId: String,
    override val skuCatalogName: String?,
) : SKUGenerationContainer

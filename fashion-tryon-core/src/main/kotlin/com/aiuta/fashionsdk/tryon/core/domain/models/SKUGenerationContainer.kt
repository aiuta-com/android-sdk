package com.aiuta.fashionsdk.tryon.core.domain.models

import android.net.Uri
import com.aiuta.fashionsdk.tryon.core.FashionTryOn

/**
 * Container for starting sku generation flow
 *
 * @see FashionTryOn.startSKUGeneration
 */
public class SKUGenerationContainer(
    public val fileUri: Uri,
    public val skuId: String,
    public val skuCatalogName: String? = null,
)

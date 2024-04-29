package com.aiuta.fashionsdk.tryon.compose.domain.models

import androidx.compose.runtime.Immutable
import java.util.UUID

/**
 * Provided sku for generation with meta info
 *
 * @param skuId - Id of sku to generate
 * @param catalogName - Name of catalog with current [skuId]
 * @param description - Small description about provided sku
 * @param imageUrls - List of urls with images of provided sku
 * @param price - Original price per sku
 * @param priceDiscounted - Price with discount of sku, can be optional
 * @param priceCurrency - Local currency of the sku
 * @param store - Name of store
 * @param generateMoreSKU - SKU for continuous generations
 */
@Immutable
public data class SKUItem(
    public val skuId: String,
    public val catalogName: String? = null,
    public val description: String,
    public val imageUrls: List<String>,
    public val price: Float,
    public val priceDiscounted: Float? = null,
    public val priceCurrency: String,
    public val store: String,
    public val generateMoreSKU: List<SKUItem>? = null,
    public val additionalShareInfo: String? = null,
) {
    internal val priceWithCurrency = "$priceCurrency$price"
    internal val priceDiscountedWithCurrency = "$priceCurrency$priceDiscounted"
    internal val uniqueGeneratedId: String = UUID.randomUUID().toString()
}

internal val defaultSKUItem =
    SKUItem(
        skuId = "default_sku_id",
        description = "default_description",
        imageUrls = emptyList(),
        price = 0f,
        priceCurrency = "\$",
        store = "default_store",
    )

package com.aiuta.fashionsdk.tryon.compose.configuration.models.product

import androidx.compose.runtime.Immutable

/**
 * Provided sku for generation with meta info
 *
 * @param skuId - Id of sku to generate
 * @param catalogName - Name of catalog with current [skuId]
 * @param description - Small description about provided sku
 * @param imageUrls - List of urls with images of provided sku
 * @param localizedPrice - The price of the SKU. Should be formatted with a currency symbol.
 * @param localizedOldPrice - The old price of the SKU, if available. Should be formatted with a currency symbol.
 * @param store - Name of store
 */
@Immutable
public data class SKUItem(
    public val skuId: String,
    public val catalogName: String? = null,
    public val description: String,
    public val imageUrls: List<String>,
    public val localizedPrice: String,
    public val localizedOldPrice: String? = null,
    public val store: String,
)

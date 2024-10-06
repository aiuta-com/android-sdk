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
 * @param localizedPrice - The price of the SKU. Should be formatted with a currency symbol.
 * @param localizedOldPrice - The old price of the SKU, if available. Should be formatted with a currency symbol.
 * @param localizedDiscount - The discount on the SKU, if available.
 * @param store - Name of store
 * @param generateMoreSKU - SKU for continuous generations
 * @param additionalShareInfo - Some additional info for sharing generated item
 */
@Immutable
public data class SKUItem(
    public val skuId: String,
    public val catalogName: String? = null,
    public val description: String,
    public val imageUrls: List<String>,
    public val localizedPrice: String,
    public val localizedOldPrice: String? = null,
    public val localizedDiscount: String? = null,
    public val store: String,
    public val generateMoreSKU: List<SKUItem>? = null,
    public val additionalShareInfo: String? = null,
    public val inWishlist: Boolean,
) {
    internal val uniqueGeneratedId: String = UUID.randomUUID().toString()
}

internal val defaultSKUItem =
    SKUItem(
        skuId = "default_sku_id",
        description = "default_description",
        imageUrls = emptyList(),
        localizedPrice = "0",
        store = "default_store",
        inWishlist = false,
    )

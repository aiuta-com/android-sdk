package com.aiuta.fashionsdk.tryon.compose.domain.models

import androidx.compose.runtime.Immutable

/**
 * Meta info about provided sku for generation
 *
 * @param description - Small description about provided sku
 * @param imageUrls - List of urls with images of provided sku
 * @param price - Original price per sku
 * @param priceDiscounted - Price with discount of sku, can be optional
 * @param priceCurrency - Local currency of the sku
 * @param store - Name of store
 */
@Immutable
public class SKUMetaInfo(
    public val description: String,
    public val imageUrls: List<String>,
    public val price: Float,
    public val priceDiscounted: Float? = null,
    public val priceCurrency: String,
    public val store: String,
) {
    internal val priceWithCurrency = "$priceCurrency$price"
    internal val priceDiscountedWithCurrency = "$priceCurrency$priceDiscounted"
}

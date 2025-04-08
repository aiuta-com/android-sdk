package com.aiuta.fashionsdk.tryon.compose.configuration.models.product

import androidx.compose.runtime.Immutable

/**
 * Provided product for generation with meta info
 *
 * @param id - Id of product to generate
 * @param catalogName - Name of catalog with current [id]
 * @param description - Small description about provided product
 * @param imageUrls - List of urls with images of provided product
 * @param localizedPrice - The price of the product. Should be formatted with a currency symbol.
 * @param localizedOldPrice - The old price of the product, if available. Should be formatted with a currency symbol.
 * @param store - Name of store
 */
@Immutable
public data class ProductItem(
    public val id: String,
    public val catalogName: String? = null,
    public val description: String,
    public val imageUrls: List<String>,
    public val localizedPrice: String,
    public val localizedOldPrice: String? = null,
    public val store: String,
)

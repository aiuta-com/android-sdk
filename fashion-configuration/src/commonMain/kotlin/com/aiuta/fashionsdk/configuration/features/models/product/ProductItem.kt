package com.aiuta.fashionsdk.configuration.features.models.product

import androidx.compose.runtime.Immutable

/**
 * Represents a product item in the SDK's product catalog.
 *
 * This immutable data class encapsulates all essential information about a product,
 * including its identification, description, images, pricing, and store information.
 * It is used throughout the SDK for product display and try-on generation.
 *
 * @property id Unique identifier for the product
 * @property catalogName Optional name of the catalog containing this product
 * @property description Brief description of the product
 * @property imageUrls List of URLs to product images
 * @property localizedPrice Current price of the product, formatted with currency symbol
 * @property localizedOldPrice Optional previous price of the product, formatted with currency symbol
 * @property store Name of the store offering the product
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

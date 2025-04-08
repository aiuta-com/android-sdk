package com.aiuta.fashionsdk.tryon.compose.configuration.listeners

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.configuration.models.product.ProductItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Callback listeners for handling specific actions
 * inside Aiuta Try-On flow.
 *
 * @param addToWishlistClick - user click on 'Add to wishlist' button
 * @param addToCartClick - user click on 'Add to cart' button
 * @param closeClick - user click close button or make back press, which should also
 * navigate back to host app
 */
@Deprecated("Migrate to separate providers")
@Immutable
public class AiutaTryOnListeners(
    public val addToWishlistClick: (productItem: ProductItem) -> Unit,
    public val addToCartClick: (productItem: ProductItem) -> Unit,
    public val closeClick: (productItem: ProductItem) -> Unit,
) {
    private val internalUpdatedActiveProductItem: MutableStateFlow<ProductItem?> = MutableStateFlow(null)
    public val updatedActiveProductItem: StateFlow<ProductItem?> = internalUpdatedActiveProductItem

    public fun updateActiveSKUItem(productItem: ProductItem) {
        internalUpdatedActiveProductItem.value = productItem
    }
}

public val DefaultAiutaTryOnListeners: AiutaTryOnListeners by lazy {
    AiutaTryOnListeners(
        addToWishlistClick = {},
        addToCartClick = {},
        closeClick = {},
    )
}

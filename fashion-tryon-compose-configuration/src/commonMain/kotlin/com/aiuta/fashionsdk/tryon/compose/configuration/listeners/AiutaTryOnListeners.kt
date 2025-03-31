package com.aiuta.fashionsdk.tryon.compose.configuration.listeners

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.configuration.models.product.SKUItem
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
@Immutable
public class AiutaTryOnListeners(
    public val addToWishlistClick: (skuItem: SKUItem) -> Unit,
    public val addToCartClick: (skuItem: SKUItem) -> Unit,
    public val closeClick: (skuItem: SKUItem) -> Unit,
) {
    private val internalUpdatedActiveSKUItem: MutableStateFlow<SKUItem?> = MutableStateFlow(null)
    public val updatedActiveSKUItem: StateFlow<SKUItem?> = internalUpdatedActiveSKUItem

    public fun updateActiveSKUItem(skuItem: SKUItem) {
        internalUpdatedActiveSKUItem.value = skuItem
    }
}

public val DefaultAiutaTryOnListeners: AiutaTryOnListeners by lazy {
    AiutaTryOnListeners(
        addToWishlistClick = {},
        addToCartClick = {},
        closeClick = {},
    )
}

package com.aiuta.fashionsdk.tryon.compose.domain.models

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.ui.AiutaTryOnFlow

// TODO Update docs

/**
 * Callback listeners for handling specific actions
 * inside [AiutaTryOnFlow]
 *
 * @param addToWishlistGenerateMoreItemClick - use click on 'Add to Wishlist' button
 * @param addToCartClick - use click on 'Add to cart' button
 * @param closeClick - use click close button or make back press, which should also
 * navigate back to host app
 */
@Immutable
public class AiutaTryOnListeners(
    public val addToWishlistActiveItemClick: suspend (skuItem: SKUItem) -> SKUItem,
    public val addToWishlistGenerateMoreItemClick: suspend (skuItem: SKUItem) -> SKUItem,
    public val addToCartClick: (skuItem: SKUItem) -> Unit,
    public val closeClick: (skuItem: SKUItem) -> Unit,
)

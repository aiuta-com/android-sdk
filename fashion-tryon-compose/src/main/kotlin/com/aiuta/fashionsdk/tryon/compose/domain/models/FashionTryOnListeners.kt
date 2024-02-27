package com.aiuta.fashionsdk.tryon.compose.domain.models

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.ui.FashionTryOnFlow

/**
 * Callback listeners for handling specific actions
 * inside [FashionTryOnFlow]
 *
 * @param addToWishlistClick - use click on 'Add to Wishlist' button
 * @param addToCartClick - use click on 'Add to cart' button
 * @param closeClick - use click close button or make back press, which should also
 * navigate back to host app
 */
@Immutable
public class FashionTryOnListeners(
    public val addToWishlistClick: () -> Unit,
    public val addToCartClick: () -> Unit,
    public val closeClick: () -> Unit,
)

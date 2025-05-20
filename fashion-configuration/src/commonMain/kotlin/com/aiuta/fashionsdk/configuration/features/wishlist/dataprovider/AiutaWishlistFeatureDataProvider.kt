package com.aiuta.fashionsdk.configuration.features.wishlist.dataprovider

import kotlinx.coroutines.flow.StateFlow

public interface AiutaWishlistFeatureDataProvider {
    public val wishlistProductIds: StateFlow<List<String>>
    public fun setProductInWishlist(productId: String, inWishlist: Boolean)
}

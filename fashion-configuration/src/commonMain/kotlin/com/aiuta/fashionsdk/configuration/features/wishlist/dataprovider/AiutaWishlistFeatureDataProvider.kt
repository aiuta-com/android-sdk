package com.aiuta.fashionsdk.configuration.features.wishlist.dataprovider

import kotlinx.coroutines.flow.StateFlow

public interface AiutaWishlistFeatureDataProvider {
    public val productWishlistState: StateFlow<List<String>>
    public fun setWishlistStateAction(productId: String, inWishlist: Boolean)
}

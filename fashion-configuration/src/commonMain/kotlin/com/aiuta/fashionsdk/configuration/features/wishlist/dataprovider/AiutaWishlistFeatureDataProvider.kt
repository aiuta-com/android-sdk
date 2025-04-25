package com.aiuta.fashionsdk.configuration.features.wishlist.dataprovider

import com.aiuta.fashionsdk.configuration.features.models.product.ProductWishlistState
import kotlinx.coroutines.flow.StateFlow

public interface AiutaWishlistFeatureDataProvider {
    public val productWishlistState: StateFlow<ProductWishlistState>
    public val changeInWishlistStateAction: (productId: String, inWishlist: Boolean) -> Unit
}

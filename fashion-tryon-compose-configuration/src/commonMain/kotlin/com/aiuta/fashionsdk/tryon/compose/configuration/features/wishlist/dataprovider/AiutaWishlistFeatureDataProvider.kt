package com.aiuta.fashionsdk.tryon.compose.configuration.features.wishlist.dataprovider

import com.aiuta.fashionsdk.tryon.compose.configuration.models.product.ProductWishlistState
import kotlinx.coroutines.flow.StateFlow

public interface AiutaWishlistFeatureDataProvider {
    public val productWishlistState: StateFlow<ProductWishlistState>
    public val changeInWishlistStateAction: (productId: String, inWishlist: Boolean) -> Unit
}

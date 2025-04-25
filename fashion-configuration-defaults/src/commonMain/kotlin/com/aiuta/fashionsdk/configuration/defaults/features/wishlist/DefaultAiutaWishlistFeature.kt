package com.aiuta.fashionsdk.configuration.defaults.features.wishlist

import com.aiuta.fashionsdk.configuration.defaults.icons.features.wishlist.DefaultAiutaWishlistFeatureIcons
import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.features.models.product.ProductWishlistState
import com.aiuta.fashionsdk.configuration.features.wishlist.dataprovider.AiutaWishlistFeatureDataProvider
import com.aiuta.fashionsdk.configuration.features.wishlist.strings.AiutaWishlistFeatureStrings
import com.aiuta.fashionsdk.configuration.features.wishlist.wishlist
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

public fun AiutaFeatures.Builder.defaultWishlist() {
    wishlist {
        icons = DefaultAiutaWishlistFeatureIcons()
        strings = AiutaWishlistFeatureStrings.Default()
        dataProvider = object : AiutaWishlistFeatureDataProvider {
            private val _productWishlistState: MutableStateFlow<ProductWishlistState> =
                MutableStateFlow(
                    ProductWishlistState(
                        productId = "TEST_PRODUCT_ID",
                        inWishlist = false,
                    ),
                )
            override val productWishlistState: StateFlow<ProductWishlistState> =
                _productWishlistState
            override val changeInWishlistStateAction: (productId: String, inWishlist: Boolean) -> Unit =
                { productId, inWishlist ->
                    _productWishlistState.value = ProductWishlistState(
                        productId = productId,
                        inWishlist = inWishlist,
                    )
                }
        }
    }
}

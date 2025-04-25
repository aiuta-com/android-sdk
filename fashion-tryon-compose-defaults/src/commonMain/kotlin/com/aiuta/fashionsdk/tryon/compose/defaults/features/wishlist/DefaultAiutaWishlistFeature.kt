package com.aiuta.fashionsdk.tryon.compose.defaults.features.wishlist

import com.aiuta.fashionsdk.configuration.features.features.AiutaTryOnConfigurationFeatures
import com.aiuta.fashionsdk.configuration.features.features.wishlist.dataprovider.AiutaWishlistFeatureDataProvider
import com.aiuta.fashionsdk.configuration.features.features.wishlist.strings.AiutaWishlistFeatureStrings
import com.aiuta.fashionsdk.configuration.features.features.wishlist.wishlist
import com.aiuta.fashionsdk.configuration.features.models.product.ProductWishlistState
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.wishlist.DefaultAiutaWishlistFeatureIcons
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

public fun AiutaTryOnConfigurationFeatures.Builder.defaultWishlist() {
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

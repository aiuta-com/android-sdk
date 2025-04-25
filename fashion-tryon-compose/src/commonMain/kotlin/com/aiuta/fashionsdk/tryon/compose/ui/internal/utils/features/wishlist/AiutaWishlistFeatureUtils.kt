package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.wishlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import com.aiuta.fashionsdk.configuration.features.features.wishlist.AiutaWishlistFeature

@Composable
internal fun AiutaWishlistFeature.inWishlistListener(): State<Boolean> {
    val productWishlistState = dataProvider.productWishlistState.collectAsState()
    return remember(productWishlistState.value) {
        derivedStateOf {
            productWishlistState.value.inWishlist
        }
    }
}

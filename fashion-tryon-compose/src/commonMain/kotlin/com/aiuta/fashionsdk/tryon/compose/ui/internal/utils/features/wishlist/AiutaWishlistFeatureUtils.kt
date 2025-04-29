package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.wishlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import com.aiuta.fashionsdk.configuration.features.wishlist.AiutaWishlistFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController

@Composable
internal fun AiutaWishlistFeature.inWishlistListener(): State<Boolean> {
    val controller = LocalController.current
    val productWishlistState = dataProvider.wishlistProductsIds.collectAsState()
    val activeItem = controller.activeProductItem.value

    return remember(
        productWishlistState.value,
        activeItem,
    ) {
        derivedStateOf {
            activeItem.id in productWishlistState.value
        }
    }
}

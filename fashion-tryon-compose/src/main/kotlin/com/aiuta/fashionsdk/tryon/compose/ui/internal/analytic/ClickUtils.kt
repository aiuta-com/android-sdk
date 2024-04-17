package com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic

import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.model.FinishSession
import com.aiuta.fashionsdk.internal.analytic.model.OpenHistoryScreen
import com.aiuta.fashionsdk.internal.analytic.model.ShareGeneratedImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController

// Listeners
internal fun FashionTryOnController.clickAddToWishList(origin: FinishSession.Origin) {
    aiutaTryOnListeners().addToWishlistClick(activeSKUItem.value)
    analytic.sendFinishSessionEvent(
        action = FinishSession.Action.ADD_TO_WISHLIST,
        origin = origin,
        skuItem = activeSKUItem.value,
    )
}

internal fun FashionTryOnController.clickMoreDetails(origin: FinishSession.Origin) {
    aiutaTryOnListeners().moreDetailsClick(activeSKUItem.value)
    analytic.sendFinishSessionEvent(
        action = FinishSession.Action.SHOW_SKU_INFO,
        origin = origin,
        skuItem = activeSKUItem.value,
    )
}

internal fun FashionTryOnController.clickAddToCart(origin: FinishSession.Origin) {
    aiutaTryOnListeners().addToCartClick(activeSKUItem.value)
    analytic.sendFinishSessionEvent(
        action = FinishSession.Action.ADD_TO_CART,
        origin = origin,
        skuItem = activeSKUItem.value,
    )
}

internal fun FashionTryOnController.clickClose(origin: FinishSession.Origin) {
    aiutaTryOnListeners().closeClick(activeSKUItem.value)
    analytic.sendFinishSessionEvent(
        action = FinishSession.Action.NONE,
        origin = origin,
        skuItem = activeSKUItem.value,
    )
}

// Senders
internal fun FashionTryOnController.sendOpenHistoryScreen() {
    analytic.sendEvent(OpenHistoryScreen)
}

internal fun InternalAiutaAnalytic.sendFinishSessionEvent(
    action: FinishSession.Action,
    origin: FinishSession.Origin,
    skuItem: SKUItem,
) {
    sendEvent(FinishSession) {
        put(
            key = FinishSession.ACTION_PARAM,
            value = action.value,
        )
        put(
            key = FinishSession.ORIGIN_PARAM,
            value = origin.value,
        )
        put(
            key = FinishSession.SKU_ID_PARAM,
            value = skuItem.skuId,
        )
        put(
            key = FinishSession.SKU_CATALOG_NAME_PARAM,
            value = skuItem.catalogName,
        )
    }
}

internal fun FashionTryOnController.sendShareGeneratedImageEvent(
    origin: ShareGeneratedImage.Origin,
    count: Int,
) {
    analytic.sendEvent(ShareGeneratedImage) {
        put(
            key = ShareGeneratedImage.ORIGIN_PARAM,
            value = origin.value,
        )
        put(
            key = ShareGeneratedImage.COUNT_PARAM,
            value = count.toString(),
        )
    }
}

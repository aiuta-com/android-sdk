package com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic

import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.model.FinishSession
import com.aiuta.fashionsdk.internal.analytic.model.OpenHistoryScreen
import com.aiuta.fashionsdk.internal.analytic.model.ShareGeneratedImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController

// Listeners
internal fun FashionTryOnController.clickAddToWishList(origin: FinishSession.Origin) {
    analytic.sendFinishSessionEvent(
        action = FinishSession.Action.ADD_TO_WISHLIST,
        origin = origin,
        skuItem = activeSKUItem.value,
    )
    aiutaTryOnListeners().addToWishlistClick(activeSKUItem.value)
}

internal fun FashionTryOnController.clickMoreDetails(origin: FinishSession.Origin) {
    analytic.sendFinishSessionEvent(
        action = FinishSession.Action.SHOW_SKU_INFO,
        origin = origin,
        skuItem = activeSKUItem.value,
    )
    aiutaTryOnListeners().moreDetailsClick(activeSKUItem.value)
}

internal fun FashionTryOnController.clickAddToCart(origin: FinishSession.Origin) {
    analytic.sendFinishSessionEvent(
        action = FinishSession.Action.ADD_TO_CART,
        origin = origin,
        skuItem = activeSKUItem.value,
    )
    aiutaTryOnListeners().addToCartClick(activeSKUItem.value)
}

internal fun FashionTryOnController.clickClose(origin: FinishSession.Origin) {
    analytic.sendFinishSessionEvent(
        action = FinishSession.Action.NONE,
        origin = origin,
        skuItem = activeSKUItem.value,
    )
    aiutaTryOnListeners().closeClick(activeSKUItem.value)
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
    additionalShareInfo: String? = null,
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
        put(
            key = ShareGeneratedImage.ADDITIONAL_SHARE_INFO_PARAM,
            value = additionalShareInfo,
        )
    }
}

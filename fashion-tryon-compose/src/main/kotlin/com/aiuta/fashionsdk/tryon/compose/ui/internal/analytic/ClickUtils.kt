package com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic

import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageEvent
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.FinishSession
import com.aiuta.fashionsdk.internal.analytic.model.ShareGeneratedImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController

// Listeners
internal fun FashionTryOnController.clickAddToWishListActiveSKU() {
    aiutaTryOnListeners().addToWishlistClick(activeSKUItem.value)
}

internal fun FashionTryOnController.clickAddToWishListGenerateMoreItem(skuItem: SKUItem) {
    aiutaTryOnListeners().addToWishlistClick(skuItem)
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
    analytic.sendEvent(
        event =
            AiutaAnalyticPageEvent(
                pageId = AiutaAnalyticPageId.HISTORY,
            ),
    )
}

internal fun InternalAiutaAnalytic.sendFinishSessionEvent(
    action: FinishSession.Action,
    origin: FinishSession.Origin,
    skuItem: SKUItem,
) {
    sendEvent(
        event =
            FinishSession(
                action = action.value,
                origin = origin.value,
                skuId = skuItem.skuId,
                skuCatalogName = skuItem.catalogName,
            ),
    )
}

internal fun FashionTryOnController.sendShareGeneratedImageEvent(
    origin: ShareGeneratedImage.Origin,
    count: Int,
    additionalShareInfo: String? = null,
) {
    analytic.sendEvent(
        event =
            ShareGeneratedImage(
                origin = origin.value,
                count = count.toString(),
                additionalShareInfo = additionalShareInfo,
            ),
    )
}

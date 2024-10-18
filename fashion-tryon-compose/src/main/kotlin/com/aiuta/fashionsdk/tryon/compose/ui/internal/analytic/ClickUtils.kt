package com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic

import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticExitEvent
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsResultsEventType
import com.aiuta.fashionsdk.internal.analytic.model.FinishSession
import com.aiuta.fashionsdk.internal.analytic.model.ShareGeneratedImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic.sendResultEvent

// Listeners
internal fun FashionTryOnController.clickAddToWishListActiveSKU(skuId: String) {
    sendResultEvent(
        event = AiutaAnalyticsResultsEventType.PRODUCT_ADD_TO_WISHLIST,
        productId = skuId,
    )
    aiutaTryOnListeners().addToWishlistClick(activeSKUItem.value)
}

internal fun FashionTryOnController.clickAddToWishListGenerateMoreItem(skuItem: SKUItem) {
    sendResultEvent(
        event = AiutaAnalyticsResultsEventType.PRODUCT_ADD_TO_WISHLIST,
        productId = skuItem.skuId,
    )
    aiutaTryOnListeners().addToWishlistClick(skuItem)
}

internal fun FashionTryOnController.clickAddToCart(
    origin: FinishSession.Origin,
    pageId: AiutaAnalyticPageId,
    skuId: String,
) {
    sendResultEvent(
        event = AiutaAnalyticsResultsEventType.PRODUCT_ADD_TO_CART,
        productId = skuId,
    )
    analytic.sendFinishSessionEvent(
        action = FinishSession.Action.ADD_TO_CART,
        origin = origin,
        pageId = pageId,
        skuItem = activeSKUItem.value,
    )
    aiutaTryOnListeners().addToCartClick(activeSKUItem.value)
}

internal fun FashionTryOnController.clickClose(
    origin: FinishSession.Origin,
    pageId: AiutaAnalyticPageId,
) {
    analytic.sendFinishSessionEvent(
        action = FinishSession.Action.NONE,
        origin = origin,
        pageId = pageId,
        skuItem = activeSKUItem.value,
    )
    aiutaTryOnListeners().closeClick(activeSKUItem.value)
}

// Senders
internal fun InternalAiutaAnalytic.sendFinishSessionEvent(
    action: FinishSession.Action,
    origin: FinishSession.Origin,
    pageId: AiutaAnalyticPageId,
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
    sendEvent(event = AiutaAnalyticExitEvent(pageId = pageId))
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

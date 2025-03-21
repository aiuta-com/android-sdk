package com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic

import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticExitEvent
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsResultsEventType
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic.sendResultEvent

// Listeners
internal fun FashionTryOnController.clickAddToWishListActiveSKU(
    pageId: AiutaAnalyticPageId,
    skuId: String,
) {
    sendResultEvent(
        event = AiutaAnalyticsResultsEventType.PRODUCT_ADD_TO_WISHLIST,
        pageId = pageId,
        productId = skuId,
    )
    aiutaTryOnListeners.addToWishlistClick(activeSKUItem.value)
}

internal fun FashionTryOnController.clickAddToWishListGenerateMoreItem(skuItem: SKUItem) {
    sendResultEvent(
        event = AiutaAnalyticsResultsEventType.PRODUCT_ADD_TO_WISHLIST,
        pageId = AiutaAnalyticPageId.RESULTS,
        productId = skuItem.skuId,
    )
    aiutaTryOnListeners.addToWishlistClick(skuItem)
}

internal fun FashionTryOnController.clickAddToCart(
    pageId: AiutaAnalyticPageId,
    skuId: String,
) {
    sendResultEvent(
        event = AiutaAnalyticsResultsEventType.PRODUCT_ADD_TO_CART,
        pageId = pageId,
        productId = skuId,
    )
    aiutaTryOnListeners.addToCartClick(activeSKUItem.value)
}

internal fun FashionTryOnController.clickClose(pageId: AiutaAnalyticPageId? = null) {
    analytic.sendFinishSessionEvent(
        pageId = pageId ?: currentScreen.value.exitPageId,
        skuItem = activeSKUItem.value,
    )
    aiutaTryOnListeners.closeClick(activeSKUItem.value)
}

// Senders
internal fun InternalAiutaAnalytic.sendFinishSessionEvent(
    pageId: AiutaAnalyticPageId,
    skuItem: SKUItem,
) {
    sendEvent(event = AiutaAnalyticExitEvent(pageId = pageId, productId = skuItem.skuId))
}

package com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic

import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticExitEvent
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsResultsEventType
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.dataprovider.AiutaTryOnFeatureDataProvider
import com.aiuta.fashionsdk.tryon.compose.configuration.features.wishlist.dataprovider.AiutaWishlistFeatureDataProvider
import com.aiuta.fashionsdk.tryon.compose.configuration.models.product.SKUItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic.sendResultEvent

// Listeners
internal fun FashionTryOnController.clickAddToWishListActiveSKU(
    pageId: AiutaAnalyticPageId,
    skuId: String,
    updatedWishlistState: Boolean,
    dataProvider: AiutaWishlistFeatureDataProvider,
) {
    sendResultEvent(
        event = AiutaAnalyticsResultsEventType.PRODUCT_ADD_TO_WISHLIST,
        pageId = pageId,
        productId = skuId,
    )
    dataProvider.changeInWishlistStateAction(skuId, updatedWishlistState)
}

internal fun FashionTryOnController.clickAddToCart(
    pageId: AiutaAnalyticPageId,
    skuId: String,
    dataProvider: AiutaTryOnFeatureDataProvider,
) {
    sendResultEvent(
        event = AiutaAnalyticsResultsEventType.PRODUCT_ADD_TO_CART,
        pageId = pageId,
        productId = skuId,
    )
    dataProvider.addToCartClick(activeSKUItem.value)
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

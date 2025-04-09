package com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic

import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticExitEvent
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsResultsEventType
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.dataprovider.AiutaTryOnFeatureDataProvider
import com.aiuta.fashionsdk.tryon.compose.configuration.features.wishlist.dataprovider.AiutaWishlistFeatureDataProvider
import com.aiuta.fashionsdk.tryon.compose.configuration.models.product.ProductItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic.sendResultEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.dataprovider.safeInvoke

// Listeners
internal fun FashionTryOnController.clickAddToWishListActiveSKU(
    pageId: AiutaAnalyticPageId,
    productId: String,
    updatedWishlistState: Boolean,
    dataProvider: AiutaWishlistFeatureDataProvider,
) {
    sendResultEvent(
        event = AiutaAnalyticsResultsEventType.PRODUCT_ADD_TO_WISHLIST,
        pageId = pageId,
        productId = productId,
    )
    dataProvider.changeInWishlistStateAction(productId, updatedWishlistState)
}

internal fun FashionTryOnController.clickAddToCart(
    pageId: AiutaAnalyticPageId,
    productId: String,
    dataProvider: AiutaTryOnFeatureDataProvider,
) {
    sendResultEvent(
        event = AiutaAnalyticsResultsEventType.PRODUCT_ADD_TO_CART,
        pageId = pageId,
        productId = productId,
    )
    dataProvider.addToCartClick.safeInvoke(activeProductItem.value)
}

internal fun FashionTryOnController.clickClose(pageId: AiutaAnalyticPageId? = null) {
    analytic.sendFinishSessionEvent(
        pageId = pageId ?: currentScreen.value.exitPageId,
        productItem = activeProductItem.value,
    )
    aiutaUserInterfaceActions.closeClick()
}

// Senders
internal fun InternalAiutaAnalytic.sendFinishSessionEvent(
    pageId: AiutaAnalyticPageId,
    productItem: ProductItem,
) {
    sendEvent(event = AiutaAnalyticExitEvent(pageId = pageId, productId = productItem.id))
}

package com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.skuinfo.analytic

import com.aiuta.fashionsdk.internal.analytic.model.TapMoreToTryOn
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController

internal fun FashionTryOnController.sendTapMoreToTryOnEvent(skuItem: SKUItem) {
    analytic.sendEvent(TapMoreToTryOn) {
        put(
            key = TapMoreToTryOn.SKU_ID_PARAM,
            value = skuItem.skuId,
        )
        put(
            key = TapMoreToTryOn.SKU_CATALOG_NAME_PARAM,
            value = skuItem.catalogName,
        )
    }
}

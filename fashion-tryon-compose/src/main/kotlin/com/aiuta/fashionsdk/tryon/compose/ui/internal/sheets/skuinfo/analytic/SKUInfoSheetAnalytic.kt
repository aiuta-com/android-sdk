package com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.skuinfo.analytic

import com.aiuta.fashionsdk.internal.analytic.model.TapMoreToTryOn
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController

internal fun FashionTryOnController.sendTapMoreToTryOnEvent(skuItem: SKUItem) {
    analytic.sendEvent(
        event =
            TapMoreToTryOn(
                skuId = skuItem.skuId,
                skuCatalogName = skuItem.catalogName,
            ),
    )
}

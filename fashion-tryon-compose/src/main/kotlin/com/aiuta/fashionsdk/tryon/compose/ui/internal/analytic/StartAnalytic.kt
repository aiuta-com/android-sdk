package com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic

import com.aiuta.fashionsdk.internal.analytic.model.StartEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController

internal fun FashionTryOnController.sendStartEvent(origin: StartEvent.TryOnOrigin) {
    analytic.sendEvent(
        event =
            StartEvent(
                productId = activeSKUItem.value.skuId,
                origin = origin,
            ),
    )
}

package com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic

import com.aiuta.fashionsdk.internal.analytic.model.StartTryOnEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController

internal fun FashionTryOnController.sendStartEvent(origin: StartTryOnEvent.TryOnOrigin) {
    analytic.sendEvent(
        event =
        StartTryOnEvent(
            productId = activeSKUItem.value.skuId,
            origin = origin,
        ),
    )
}

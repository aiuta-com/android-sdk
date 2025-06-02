package com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.aiuta.fashionsdk.internal.analytic.model.AiutaSessionEvent
import com.aiuta.fashionsdk.internal.analytic.model.TerminateEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController

@Composable
internal fun sendSessionEvent(flow: AiutaSessionEvent.FlowType) {
    val controller = LocalController.current

    LaunchedEffect(Unit) {
        with(controller) {
            analytic.sendEvent(
                event = AiutaSessionEvent(
                    flow = flow,
                    productId = activeProductItem.value.id,
                ),
            )
        }
    }
}

internal fun FashionTryOnController.sendTerminateEvent() {
    analytic.sendEvent(
        event = TerminateEvent(
            productId = activeProductItem.value.id,
        ),
    )
}

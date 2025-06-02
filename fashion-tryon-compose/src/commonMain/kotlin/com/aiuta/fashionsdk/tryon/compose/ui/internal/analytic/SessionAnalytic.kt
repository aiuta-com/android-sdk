package com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.aiuta.fashionsdk.analytics.events.AiutaAnalyticPageId
import com.aiuta.fashionsdk.analytics.events.AiutaAnalyticsTryOnAbortedReasonType
import com.aiuta.fashionsdk.analytics.events.AiutaAnalyticsTryOnEvent
import com.aiuta.fashionsdk.analytics.events.AiutaAnalyticsTryOnEventType
import com.aiuta.fashionsdk.analytics.events.AiutaSessionEvent
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
        event = AiutaAnalyticsTryOnEvent(
            event = AiutaAnalyticsTryOnEventType.TRY_ON_ABORTED,
            abortReason = AiutaAnalyticsTryOnAbortedReasonType.USER_CANCELED,
            pageId = AiutaAnalyticPageId.LOADING,
            productId = activeProductItem.value.id,
        ),
    )
}

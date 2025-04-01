package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.analytic

import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsHistoryEvent
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsHistoryEventType
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController

internal fun FashionTryOnController.sendHistoryEvent(eventType: AiutaAnalyticsHistoryEventType) {
    analytic.sendEvent(
        event =
        AiutaAnalyticsHistoryEvent(
            event = eventType,
            pageId = AiutaAnalyticPageId.HISTORY,
            productId = activeSKUItem.value.skuId,
        ),
    )
}

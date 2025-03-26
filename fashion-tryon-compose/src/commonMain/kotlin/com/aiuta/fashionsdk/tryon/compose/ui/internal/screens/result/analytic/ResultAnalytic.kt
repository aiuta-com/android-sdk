package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic

import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsResultsEvent
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsResultsEventType
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController

internal fun FashionTryOnController.sendResultEvent(
    event: AiutaAnalyticsResultsEventType,
    pageId: AiutaAnalyticPageId,
    productId: String,
) {
    analytic.sendEvent(
        event =
            AiutaAnalyticsResultsEvent(
                event = event,
                productId = productId,
                pageId = pageId,
            ),
    )
}

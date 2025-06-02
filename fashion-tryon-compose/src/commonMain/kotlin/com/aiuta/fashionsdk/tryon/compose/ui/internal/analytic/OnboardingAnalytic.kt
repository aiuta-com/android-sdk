package com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic

import com.aiuta.fashionsdk.analytics.events.AiutaAnalyticOnboardingEvent
import com.aiuta.fashionsdk.analytics.events.AiutaAnalyticOnboardingEventType
import com.aiuta.fashionsdk.analytics.events.AiutaAnalyticPageId
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController

internal fun FashionTryOnController.sendOnboardingEvent(
    eventType: AiutaAnalyticOnboardingEventType,
    pageId: AiutaAnalyticPageId,
    productId: String,
    consentsIds: List<String>?,
) {
    analytic.sendEvent(
        event = AiutaAnalyticOnboardingEvent(
            event = eventType,
            pageId = pageId,
            productId = productId,
            consentsIds = consentsIds?.takeIf { it.isNotEmpty() },
        ),
    )
}

package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic

import com.aiuta.fashionsdk.analytics.events.AiutaAnalyticsFeedbackEvent
import com.aiuta.fashionsdk.analytics.events.AiutaAnalyticsFeedbackEventType
import com.aiuta.fashionsdk.analytics.events.AiutaAnalyticsPageId
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController

internal fun FashionTryOnController.sendGenerationFeedback(
    optionIndex: Int,
    feedback: String? = null,
) {
    val activeSKUItem = activeProductItem.value

    analytic.sendEvent(
        event =
        AiutaAnalyticsFeedbackEvent(
            event = AiutaAnalyticsFeedbackEventType.NEGATIVE,
            negativeFeedbackOptionIndex = optionIndex,
            negativeFeedbackText = feedback,
            pageId = AiutaAnalyticsPageId.RESULTS,
            productId = activeSKUItem.id,
        ),
    )
}

internal fun FashionTryOnController.sendLikeGenerationFeedback() {
    val activeSKUItem = activeProductItem.value

    analytic.sendEvent(
        event =
        AiutaAnalyticsFeedbackEvent(
            event = AiutaAnalyticsFeedbackEventType.POSITIVE,
            pageId = AiutaAnalyticsPageId.RESULTS,
            productId = activeSKUItem.id,
        ),
    )
}

package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic

import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsFeedbackEvent
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsFeedbackEventType
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController

internal fun FashionTryOnController.sendGenerationFeedback(
    optionIndex: Int,
    feedback: String? = null,
) {
    val activeSKUItem = activeSKUItem.value

    analytic.sendEvent(
        event =
            AiutaAnalyticsFeedbackEvent(
                event = AiutaAnalyticsFeedbackEventType.NEGATIVE,
                negativeFeedbackOptionIndex = optionIndex,
                negativeFeedbackText = feedback,
                pageId = AiutaAnalyticPageId.RESULTS,
                productId = activeSKUItem.skuId,
            ),
    )
}

internal fun FashionTryOnController.sendLikeGenerationFeedback() {
    val activeSKUItem = activeSKUItem.value

    analytic.sendEvent(
        event =
            AiutaAnalyticsFeedbackEvent(
                event = AiutaAnalyticsFeedbackEventType.POSITIVE,
                pageId = AiutaAnalyticPageId.RESULTS,
                productId = activeSKUItem.skuId,
            ),
    )
}

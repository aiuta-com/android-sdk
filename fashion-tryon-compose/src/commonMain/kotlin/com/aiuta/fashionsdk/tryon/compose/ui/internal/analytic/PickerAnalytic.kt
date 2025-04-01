package com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsPickerEvent
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsPickerEventType
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController

internal fun FashionTryOnController.sendPickerAnalytic(
    event: AiutaAnalyticsPickerEventType,
    pageId: AiutaAnalyticPageId,
) {
    analytic.sendEvent(
        event =
        AiutaAnalyticsPickerEvent(
            event = event,
            pageId = pageId,
            productId = activeSKUItem.value.skuId,
        ),
    )
}

@Composable
internal fun sendPickerAnalytic(
    event: AiutaAnalyticsPickerEventType,
    pageId: AiutaAnalyticPageId,
) {
    val controller = LocalController.current

    LaunchedEffect(Unit) {
        controller.sendPickerAnalytic(
            event = event,
            pageId = pageId,
        )
    }
}

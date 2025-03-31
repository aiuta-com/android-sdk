package com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageEvent
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController

internal fun FashionTryOnController.sendPageEvent(pageId: AiutaAnalyticPageId) {
    analytic.sendEvent(
        event =
            AiutaAnalyticPageEvent(
                pageId = pageId,
                productId = activeSKUItem.value.skuId,
            ),
    )
}

@Composable
internal fun sendPageEvent(pageId: AiutaAnalyticPageId) {
    val controller = LocalController.current

    LaunchedEffect(Unit) {
        controller.sendPageEvent(pageId)
    }
}

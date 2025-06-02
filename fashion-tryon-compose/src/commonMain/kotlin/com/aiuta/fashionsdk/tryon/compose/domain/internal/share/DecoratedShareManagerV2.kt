package com.aiuta.fashionsdk.tryon.compose.domain.internal.share

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.AiutaShareEvent
import com.aiuta.fashionsdk.internal.analytic.model.AiutaShareEventType
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAnalytic

internal class DecoratedShareManagerV2(
    private val actualShareManager: ShareManagerV2,
    private val analytic: InternalAiutaAnalytic,
) : ShareManagerV2 {

    override suspend fun shareImages(
        content: String?,
        pageId: AiutaAnalyticPageId,
        productId: String?,
        imageUrls: List<String>,
        watermark: Painter?,
    ): Result<Unit> {
        // Decorate with analytic
        analytic.sendEvent(
            event = AiutaShareEvent(
                pageId = pageId,
                productId = productId,
                event = AiutaShareEventType.INITIATED,
                targetId = null,
            ),
        )

        // Call real implementation
        return actualShareManager.shareImages(
            content = content,
            pageId = pageId,
            productId = productId,
            imageUrls = imageUrls,
            watermark = watermark,
        )
    }
}

@Composable
internal fun rememberShareManagerV2(): ShareManagerV2 {
    val actualShareManager = rememberActualShareManagerV2()
    val analytic = LocalAnalytic.current

    return remember(
        actualShareManager,
        analytic,
    ) {
        DecoratedShareManagerV2(
            actualShareManager = actualShareManager,
            analytic = analytic,
        )
    }
}

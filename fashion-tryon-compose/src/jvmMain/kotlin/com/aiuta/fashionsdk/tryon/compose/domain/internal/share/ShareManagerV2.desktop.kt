package com.aiuta.fashionsdk.tryon.compose.domain.internal.share

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId

internal actual class ShareManagerV2 {
    actual suspend fun shareImages(
        content: String?,
        pageId: AiutaAnalyticPageId,
        productId: String?,
        imageUrls: List<String>,
        watermark: Painter?,
    ): Result<Unit> {
        // TODO Add check on UI side
        // This platform not support share
        return Result.success(Unit)
    }
}

@Composable
internal actual fun rememberShareManagerV2(): ShareManagerV2 = ShareManagerV2()

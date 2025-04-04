package com.aiuta.fashionsdk.tryon.compose.domain.internal.share

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId

internal expect class ShareManagerV2 {
    suspend fun shareImages(
        content: String? = null,
        pageId: AiutaAnalyticPageId,
        productId: String?,
        imageUrls: List<String>,
        watermark: Painter? = null,
    ): Result<Unit>
}

@Composable
internal expect fun rememberShareManagerV2(): ShareManagerV2

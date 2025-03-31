package com.aiuta.fashionsdk.tryon.compose.domain.internal.share

import androidx.compose.runtime.Composable
import coil3.PlatformContext
import com.aiuta.fashionsdk.compose.tokens.images.AiutaImage
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.tryon.core.domain.models.image.AiutaPlatformImage

internal expect class ShareManagerV2 {
    suspend fun shareImages(
        content: String?,
        pageId: AiutaAnalyticPageId,
        productId: String?,
        images: List<AiutaPlatformImage>,
        watermark: AiutaImage? = null,
    ): Result<Unit>

    suspend fun shareImages(
        coilContext: PlatformContext,
        content: String? = null,
        pageId: AiutaAnalyticPageId,
        productId: String?,
        imageUrls: List<String>,
        watermark: AiutaImage? = null,
    ): Result<Unit>
}

@Composable
internal expect fun rememberShareManagerV2(): ShareManagerV2

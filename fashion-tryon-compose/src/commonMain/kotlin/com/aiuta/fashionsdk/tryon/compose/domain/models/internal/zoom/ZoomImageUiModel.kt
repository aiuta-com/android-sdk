package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.zoom

import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId

@Immutable
internal class ZoomImageUiModel(
    val imageSize: Size,
    val initialCornerRadius: Dp,
    val imageUrl: String?,
    val parentImageOffset: Offset,
    // Analytic
    val originPageId: AiutaAnalyticPageId,
) {
    companion object {
        val EMPTY by lazy {
            ZoomImageUiModel(
                imageSize = Size.Unspecified,
                initialCornerRadius = 0.dp,
                imageUrl = null,
                parentImageOffset = Offset.Unspecified,
                originPageId = AiutaAnalyticPageId.RESULTS,
            )
        }
    }
}

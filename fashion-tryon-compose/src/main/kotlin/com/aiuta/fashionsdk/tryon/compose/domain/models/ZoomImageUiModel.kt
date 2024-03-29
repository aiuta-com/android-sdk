package com.aiuta.fashionsdk.tryon.compose.domain.models

import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
internal class ZoomImageUiModel(
    val imageSize: Size,
    val initialCornerRadius: Dp,
    val imageUrl: String?,
    val parentImageOffset: Offset,
) {
    companion object {
        val EMPTY =
            ZoomImageUiModel(
                imageSize = Size.Unspecified,
                initialCornerRadius = 0.dp,
                imageUrl = null,
                parentImageOffset = Offset.Unspecified,
            )
    }
}

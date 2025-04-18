package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.aiuta.fashionsdk.compose.core.size.rememberScreenSize

@Composable
internal fun calculateMinGridItemWidth(
    preferredWidth: Dp,
    minColumnsCount: Int,
    contentPadding: Dp,
    horizontalPadding: Dp,
): Dp {
    val screenSize = rememberScreenSize()

    val totalHorizontalPadding = horizontalPadding * 2
    val totalContentPadding = contentPadding * (minColumnsCount - 1)
    val totalPadding = totalHorizontalPadding + totalContentPadding

    return ((screenSize.widthDp - totalPadding) / minColumnsCount).coerceAtMost(
        maximumValue = preferredWidth,
    )
}

package com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalTheme

@Composable
internal fun LoadingProgress(
    modifier: Modifier = Modifier,
    circleSize: Dp = 24.dp,
    circleWidth: Dp = 2.dp,
) {
    val theme = LocalTheme.current

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier =
                Modifier
                    .size(circleSize)
                    .align(Alignment.Center),
            color = theme.colors.accent,
            strokeWidth = circleWidth,
        )
    }
}

package com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme

@Composable
internal fun LoadingProgress(
    modifier: Modifier = Modifier,
    circleSize: Dp = 24.dp,
    circleWidth: Dp = 2.dp,
    circleColor: Color = LocalTheme.current.colors.brand,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier =
                Modifier
                    .size(circleSize)
                    .align(Alignment.Center),
            color = circleColor,
            strokeWidth = circleWidth,
        )
    }
}

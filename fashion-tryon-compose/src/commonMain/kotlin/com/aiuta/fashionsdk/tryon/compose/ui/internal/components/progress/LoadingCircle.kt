package com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.molecules.images.AiutaIcon
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme

@Composable
internal fun LoadingProgress(
    modifier: Modifier = Modifier,
    circleSize: Dp = 24.dp,
    circleColor: Color = LocalTheme.current.colors.brand,
) {
    val theme = LocalTheme.current

    val infiniteTransition = rememberInfiniteTransition()
    val angle =
        infiniteTransition.animateFloat(
            initialValue = 0F,
            targetValue = 360F,
            animationSpec =
            infiniteRepeatable(
                animation = tween(2000, easing = LinearEasing),
            ),
        )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        AiutaIcon(
            modifier =
            Modifier
                .size(circleSize)
                .align(Alignment.Center)
                .rotate(angle.value),
            icon = theme.icons.loading14,
            tint = circleColor,
            contentDescription = null,
        )
    }
}

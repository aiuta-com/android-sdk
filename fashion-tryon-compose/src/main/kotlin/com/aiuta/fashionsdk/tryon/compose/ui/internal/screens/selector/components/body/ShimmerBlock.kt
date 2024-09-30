package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components.body

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme

@Composable
internal fun ShimmerBlock(
    modifier: Modifier = Modifier,
    durationMillis: Int = 1500,
    lineHeight: Dp = 4.dp,
) {
    val theme = LocalTheme.current
    val density = LocalDensity.current

    val transition = rememberInfiniteTransition()
    val translateAnimation =
        transition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec =
                infiniteRepeatable(
                    animation =
                        tween(
                            durationMillis = durationMillis,
                            easing = LinearEasing,
                        ),
                    repeatMode = RepeatMode.Reverse,
                ),
            label = "Shimmer loading animation",
        )

    val loadingAnimationGradient =
        remember {
            theme.gradients.loadingAnimation.map {
                if (it != Color.Transparent) {
                    it.copy(alpha = 0.5f)
                } else {
                    it
                }
            }
        }
    val reversedLoadingAnimationGradient =
        remember {
            loadingAnimationGradient.reversed()
        }

    Canvas(modifier = modifier) {
        // Up gradient
        drawRect(
            brush =
                Brush.verticalGradient(
                    reversedLoadingAnimationGradient,
                    startY = 0f,
                    endY = size.height * translateAnimation.value,
                ),
            topLeft = Offset(0f, 0f),
            size =
                Size(
                    width = size.width,
                    height = size.height * translateAnimation.value,
                ),
        )

        // Main line
        drawRect(
            color = loadingAnimationGradient.first(),
            topLeft = Offset(0f, size.height * translateAnimation.value),
            size =
                Size(
                    width = size.width,
                    height = with(density) { lineHeight.toPx() },
                ),
        )

        // Down gradient
        drawRect(
            brush =
                Brush.verticalGradient(
                    loadingAnimationGradient,
                    startY = size.height * translateAnimation.value,
                    endY = size.height * translateAnimation.value + size.height * (1 - translateAnimation.value),
                ),
            topLeft = Offset(0f, size.height * translateAnimation.value),
            size =
                Size(
                    width = size.width,
                    height = size.height * (1 - translateAnimation.value),
                ),
        )
    }
}

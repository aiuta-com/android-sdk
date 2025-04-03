package com.aiuta.fashionsdk.tryon.compose.ui.internal.components.icons

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import com.aiuta.fashionsdk.compose.molecules.images.AiutaIcon
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.loading.AiutaTryOnLoadingPageFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.provideFeature

@Composable
internal fun AiutaLoadingIcon(
    modifier: Modifier = Modifier,
    circleColor: Color = LocalTheme.current.colors.brand,
) {
    val loadingPageFeature = provideFeature<AiutaTryOnLoadingPageFeature>()

    loadingPageFeature?.icons?.loading14?.let { loading14 ->
        val infiniteTransition = rememberInfiniteTransition()
        val angle = infiniteTransition.animateFloat(
            initialValue = 0F,
            targetValue = 360F,
            animationSpec =
            infiniteRepeatable(
                animation = tween(2000, easing = LinearEasing),
            ),
        )

        AiutaIcon(
            modifier = modifier.rotate(angle.value),
            icon = loading14,
            tint = circleColor,
            contentDescription = null,
        )
    } ?: CircularProgressIndicator(modifier = modifier, color = circleColor)
}

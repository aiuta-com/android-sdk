package com.aiuta.fashionsdk.compose.tokens.utils

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import io.github.fornewid.placeholder.foundation.PlaceholderHighlight
import io.github.fornewid.placeholder.foundation.fade
import io.github.fornewid.placeholder.foundation.placeholder

@Composable
internal fun Modifier.placeholderFadeConnecting(
    shape: CornerBasedShape = RoundedCornerShape(4.dp),
    visible: Boolean = true,
): Modifier {
    val theme = LocalTheme.current
    val placeholderModifier = Modifier.placeholder(
        visible = visible,
        shape = shape,
        color = theme.colors.neutral.copy(alpha = 0.2f),
        highlight = PlaceholderHighlight.fade(
            highlightColor = theme.colors.neutral,
        ),
    )

    return this then placeholderModifier
}

@Composable
internal fun Modifier.placeholderFadeConnecting(
    shapeDp: Dp = 4.dp,
    visible: Boolean = true,
): Modifier = this.placeholderFadeConnecting(shape = RoundedCornerShape(shapeDp), visible = visible)

package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated

@Composable
internal fun IconButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    onClick: () -> Unit,
) {
    val theme = LocalTheme.current

    Box(
        modifier =
            modifier
                .size(38.dp)
                .background(
                    color = theme.colors.background,
                    shape = CircleShape,
                )
                .clickableUnindicated { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painter,
            contentDescription = null,
            tint = Color.Unspecified,
        )
    }
}

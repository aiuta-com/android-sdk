package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaIcon
import com.aiuta.fashionsdk.tryon.compose.uikit.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.uikit.resources.AiutaIcon
import com.aiuta.fashionsdk.tryon.compose.uikit.utils.clickableUnindicated

@Composable
internal fun SmallIconButton(
    modifier: Modifier = Modifier,
    icon: AiutaIcon,
    isActive: Boolean = true,
    onClick: () -> Unit,
) {
    val theme = LocalTheme.current
    val backgroundColor by animateColorAsState(
        targetValue = if (isActive) theme.color.onDark else theme.color.onDark.copy(alpha = 0.4f),
        label = "backgroundColor",
    )

    Box(
        modifier =
        modifier
            .size(36.dp)
            .background(
                color = backgroundColor,
                shape = CircleShape,
            )
            .clickableUnindicated {
                if (isActive) {
                    onClick()
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        AiutaIcon(
            modifier = Modifier.size(24.dp),
            icon = icon,
            contentDescription = null,
            tint = theme.color.primary,
        )
    }
}

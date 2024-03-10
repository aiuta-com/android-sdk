package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalTheme

@Composable
internal fun SmallIconButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    isActive: Boolean = true,
    onClick: () -> Unit,
) {
    val theme = LocalTheme.current
    val iconColor by animateColorAsState(
        targetValue = if (isActive) theme.colors.onLight else theme.colors.gray3,
        label = "iconColor",
    )

    Box(
        modifier =
            modifier
                .size(36.dp)
                .background(
                    color = theme.colors.onDark,
                    shape = CircleShape,
                )
                .clickableUnindicated {
                    if (isActive) {
                        onClick()
                    }
                },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = ImageVector.vectorResource(iconRes),
            contentDescription = null,
            tint = iconColor,
        )
    }
}

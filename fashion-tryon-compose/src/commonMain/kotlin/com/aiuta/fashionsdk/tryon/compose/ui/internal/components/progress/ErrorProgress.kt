package com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.molecules.images.AiutaIcon
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme

@Composable
internal fun ErrorProgress(
    modifier: Modifier = Modifier,
    background: Color = LocalTheme.current.colors.neutral,
    iconTint: Color = LocalTheme.current.colors.primary,
) {
    val theme = LocalTheme.current

    Box(
        modifier = modifier.clipToBounds().background(background),
        contentAlignment = Alignment.Center,
    ) {
        AiutaIcon(
            modifier = Modifier.size(36.dp),
            icon = theme.icons.imageError36,
            contentDescription = null,
            tint = iconTint,
        )
    }
}

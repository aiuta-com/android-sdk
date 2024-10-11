package com.aiuta.fashionsdk.compose.molecules.images

import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import coil.compose.rememberAsyncImagePainter
import com.aiuta.fashionsdk.compose.tokens.icon.AiutaIcons

@Composable
public fun AiutaIcon(
    icon: AiutaIcons.AiutaIcon,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
) {
    Icon(
        painter = rememberAsyncImagePainter(icon.resource),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = if (icon.shouldDrawAsIs) Color.Unspecified else tint,
    )
}

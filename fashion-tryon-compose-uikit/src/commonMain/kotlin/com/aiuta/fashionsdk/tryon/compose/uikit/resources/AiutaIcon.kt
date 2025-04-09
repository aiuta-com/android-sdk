package com.aiuta.fashionsdk.tryon.compose.uikit.resources

import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaIcon
import com.aiuta.fashionsdk.tryon.compose.uikit.resources.painter.painterResource

@Composable
public fun AiutaIcon(
    icon: AiutaIcon,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
) {
    Icon(
        painter = painterResource(drawableResource = icon.iconResource),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = if (icon.shouldDrawAsIs) Color.Unspecified else tint,
    )
}

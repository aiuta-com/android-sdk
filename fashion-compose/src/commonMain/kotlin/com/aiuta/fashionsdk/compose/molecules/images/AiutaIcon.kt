package com.aiuta.fashionsdk.compose.molecules.images

import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import coil3.compose.rememberAsyncImagePainter
import com.aiuta.fashionsdk.compose.tokens.icon.AiutaIcon
import com.aiuta.fashionsdk.compose.tokens.icon.AiutaResourceIcon
import org.jetbrains.compose.resources.painterResource

@Deprecated("Use from another package")
@Composable
public fun AiutaIcon(
    icon: AiutaIcon,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
) {
    Icon(
        painter =
        when (icon) {
            is AiutaResourceIcon -> painterResource(icon.resource)
            else -> rememberAsyncImagePainter(icon.resource)
        },
        contentDescription = contentDescription,
        modifier = modifier,
        tint = if (icon.shouldDrawAsIs) Color.Unspecified else tint,
    )
}

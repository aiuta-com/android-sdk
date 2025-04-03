package com.aiuta.fashionsdk.compose.tokens.images

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.painter.Painter
import coil3.compose.rememberAsyncImagePainter
import org.jetbrains.compose.resources.DrawableResource

/**
 * Represents an image used in the Aiuta fashion try-on application.
 */
@Immutable
public interface AiutaImage {
    public val resource: Any?
}

/**
 * Represents an image resource identified by a drawable resource ID.
 *
 * @property resource The drawable resource ID of the image.
 */
public class AiutaResourceImage(
    public override val resource: DrawableResource,
) : AiutaImage

@Composable
public fun painterResource(icon: AiutaImage): Painter = when (icon) {
    is AiutaResourceImage -> org.jetbrains.compose.resources.painterResource(icon.resource)
    else -> rememberAsyncImagePainter(icon.resource)
}

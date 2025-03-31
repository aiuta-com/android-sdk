package com.aiuta.fashionsdk.compose.tokens.images

import androidx.compose.runtime.Immutable
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

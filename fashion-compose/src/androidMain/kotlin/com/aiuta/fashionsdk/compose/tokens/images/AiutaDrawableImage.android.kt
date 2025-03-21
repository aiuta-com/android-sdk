package com.aiuta.fashionsdk.compose.tokens.images

import android.graphics.drawable.Drawable

/**
 * Represents an image resource identified by a Drawable object.
 *
 * @property resource The Drawable object of the image.
 */
public class AiutaDrawableImage(
    public override val resource: Drawable,
) : AiutaImage

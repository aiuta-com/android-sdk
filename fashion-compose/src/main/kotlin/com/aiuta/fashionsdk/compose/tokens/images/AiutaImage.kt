package com.aiuta.fashionsdk.compose.tokens.images

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable

/**
 * Represents an image used in the Aiuta fashion try-on application.
 */
@Immutable
public sealed interface AiutaImage

/**
 * Represents an image resource identified by a drawable resource ID.
 *
 * @property resource The drawable resource ID of the image.
 */
public class AiutaResourceImage(
    @DrawableRes public val resource: Int,
) : AiutaImage

/**
 * Represents an image resource identified by a Drawable object.
 *
 * @property resource The Drawable object of the image.
 */
public class AiutaDrawableImage(
    public val resource: Drawable,
) : AiutaImage

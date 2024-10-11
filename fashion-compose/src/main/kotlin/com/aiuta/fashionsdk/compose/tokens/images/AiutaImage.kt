package com.aiuta.fashionsdk.compose.tokens.images

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable

// TODO Add docs
@Immutable
public sealed interface AiutaImage

public class AiutaResourceImage(
    @DrawableRes public val resource: Int,
) : AiutaImage

public class AiutaDrawableImage(
    public val resource: Drawable,
) : AiutaImage

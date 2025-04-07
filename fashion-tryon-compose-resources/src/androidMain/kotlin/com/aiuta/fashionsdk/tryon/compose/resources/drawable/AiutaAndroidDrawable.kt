package com.aiuta.fashionsdk.tryon.compose.resources.drawable

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

public class AiutaAndroidDrawable(
    public override val resource: Drawable,
) : AiutaDrawableResource

public class AiutaAndroidDrawableRes(
    @DrawableRes public override val resource: Int,
) : AiutaDrawableResource

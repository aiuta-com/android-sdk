package com.aiuta.fashionsdk.tryon.compose.configuration.ui.resources.icon

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.resources.AiutaDrawableResource

public class AiutaAndroidDrawable(
    public override val resource: Drawable,
) : AiutaDrawableResource

public class AiutaAndroidDrawableRes(
    @DrawableRes public override val resource: Int,
) : AiutaDrawableResource

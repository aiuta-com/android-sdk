package com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.image.shapes

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

public abstract class AiutaImageThemeShapes {
    public abstract val imageL: Dp
    public abstract val imageS: Dp

    public val imageLShape: CornerBasedShape by lazy { RoundedCornerShape(imageL) }
    public val imageSShape: CornerBasedShape by lazy { RoundedCornerShape(imageS) }

    public class Default : AiutaImageThemeShapes() {
        override val imageL: Dp = 24.dp
        override val imageS: Dp = 16.dp
    }
}

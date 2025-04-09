package com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.button.shapes

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

public abstract class AiutaButtonThemeShapes {
    public abstract val buttonM: Dp
    public abstract val buttonS: Dp

    public val buttonMShape: CornerBasedShape by lazy { RoundedCornerShape(buttonM) }
    public val buttonSShape: CornerBasedShape by lazy { RoundedCornerShape(buttonS) }

    public class Default : AiutaButtonThemeShapes() {
        override val buttonM: Dp = 8.dp
        override val buttonS: Dp = 8.dp
    }
}

package com.aiuta.fashionsdk.configuration.ui.theme.sheet.shapes

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

public abstract class AiutaBottomSheetThemeShapes {
    public abstract val bottomSheet: Dp
    public abstract val chipsButton: Dp

    public val bottomSheetShape: CornerBasedShape by lazy { RoundedCornerShape(topStart = bottomSheet, topEnd = bottomSheet) }
    public val chipsButtonShape: CornerBasedShape by lazy { RoundedCornerShape(chipsButton) }

    public class Default : AiutaBottomSheetThemeShapes() {
        override val bottomSheet: Dp = 16.dp
        override val chipsButton: Dp = 8.dp
    }
}

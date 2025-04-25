package com.aiuta.fashionsdk.configuration.ui.theme.sheet.grabber

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

public interface AiutaBottomSheetThemeGrabber {
    public val width: Dp
    public val topPadding: Dp

    public class Default : AiutaBottomSheetThemeGrabber {
        override val width: Dp = 36.dp
        override val topPadding: Dp = 8.dp
    }
}

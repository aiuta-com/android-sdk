package com.aiuta.fashionsdk.configuration.ui.theme.error.colors

import androidx.compose.ui.graphics.Color

public interface AiutaErrorSnackbarThemeColors {
    public val errorBackground: Color
    public val errorPrimary: Color

    public class Default : AiutaErrorSnackbarThemeColors {
        override val errorBackground: Color = Color(0xFFFFF5F5)
        override val errorPrimary: Color = Color(0xFF000000)
    }
}

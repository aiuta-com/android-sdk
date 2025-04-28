package com.aiuta.fashionsdk.configuration.ui.theme.powerby.colors

import androidx.compose.ui.graphics.Color

public interface AiutaPowerBarThemeColors {
    public val aiuta: Color

    public class Default : AiutaPowerBarThemeColors {
        override val aiuta: Color = Color(0xFF4000FF)
    }
}

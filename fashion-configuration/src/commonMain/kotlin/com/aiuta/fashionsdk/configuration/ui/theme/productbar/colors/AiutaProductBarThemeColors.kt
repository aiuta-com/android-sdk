package com.aiuta.fashionsdk.configuration.ui.theme.productbar.colors

import androidx.compose.ui.graphics.Color

public interface AiutaProductBarThemeColors {
    public val discountedPrice: Color

    public class Default : AiutaProductBarThemeColors {
        override val discountedPrice: Color = Color(0xFFFB1010)
    }
}

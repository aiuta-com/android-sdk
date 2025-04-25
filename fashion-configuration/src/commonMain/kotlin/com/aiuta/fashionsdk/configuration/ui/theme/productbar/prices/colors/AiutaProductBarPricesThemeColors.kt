package com.aiuta.fashionsdk.configuration.ui.theme.productbar.prices.colors

import androidx.compose.ui.graphics.Color

public interface AiutaProductBarPricesThemeColors {
    public val discountedPrice: Color

    public class Default : AiutaProductBarPricesThemeColors {
        override val discountedPrice: Color = Color(0xFFFB1010)
    }
}

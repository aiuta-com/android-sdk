package com.aiuta.fashionsdk.configuration.ui.theme.productbar.prices.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

public interface AiutaProductBarPricesThemeTypography {
    public val price: TextStyle

    public class Default : AiutaProductBarPricesThemeTypography {
        override val price: TextStyle = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 18.sp,
        )
    }
}

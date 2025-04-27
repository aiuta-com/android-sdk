package com.aiuta.fashionsdk.configuration.ui.theme.productbar.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

public interface AiutaProductBarThemeTypography {
    public val product: TextStyle
    public val brand: TextStyle

    public class Default : AiutaProductBarThemeTypography {
        override val product: TextStyle = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 18.sp,
        )
        override val brand: TextStyle = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.04.sp,
        )
    }
}

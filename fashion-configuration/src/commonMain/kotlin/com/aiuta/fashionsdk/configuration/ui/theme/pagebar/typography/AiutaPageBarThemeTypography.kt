package com.aiuta.fashionsdk.configuration.ui.theme.pagebar.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

public interface AiutaPageBarThemeTypography {
    public val pageTitle: TextStyle

    public class Default : AiutaPageBarThemeTypography {
        override val pageTitle: TextStyle = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 18.sp,
        )
    }
}

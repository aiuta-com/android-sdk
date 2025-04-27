package com.aiuta.fashionsdk.configuration.ui.theme.button.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

public interface AiutaButtonThemeTypography {
    public val buttonM: TextStyle
    public val buttonS: TextStyle

    public class Default : AiutaButtonThemeTypography {
        override val buttonM: TextStyle = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 20.sp,
        )
        override val buttonS: TextStyle = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            lineHeight = 18.sp,
        )
    }
}

package com.aiuta.fashionsdk.configuration.ui.theme.sheet.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

public interface AiutaBottomSheetThemeTypography {
    public val iconButton: TextStyle
    public val chipsButton: TextStyle

    public class Default : AiutaBottomSheetThemeTypography {
        override val iconButton: TextStyle = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 18.sp,
        )
        override val chipsButton: TextStyle = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 18.sp,
        )
    }
}

package com.aiuta.fashionsdk.tryon.compose.configuration.features.welcome.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

public interface AiutaWelcomeScreenFeatureTypography {
    public val welcomeTitle: TextStyle
    public val welcomeDescription: TextStyle

    public class Default(
        override val welcomeTitle: TextStyle,
        override val welcomeDescription: TextStyle,
    ) : AiutaWelcomeScreenFeatureTypography {
        public constructor() : this(
            welcomeTitle =
            TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Black,
                fontSize = 40.sp,
                lineHeight = 44.sp,
            ),
            welcomeDescription =
            TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 24.sp,
            ),
        )
    }
}

package com.aiuta.fashionsdk.configuration.ui.theme.color

import androidx.compose.ui.graphics.Color

public interface AiutaColorTheme {

    public val brand: Color
    public val primary: Color
    public val secondary: Color
    public val onDark: Color
    public val onLight: Color
    public val background: Color
    public val screen: Color
    public val neutral: Color
    public val border: Color
    public val outline: Color

    public class Default : AiutaColorTheme {
        override val brand: Color = Color(0xFF4000FF)
        override val primary: Color = Color(0xFF000000)
        override val secondary: Color = Color(0xFF9F9F9F)
        override val onDark: Color = Color(0xFFFFFFFF)
        override val onLight: Color = Color(0xFF000000)
        override val background: Color = Color(0xFFFFFFFF)
        override val screen: Color = Color(0xFFFFFFFF)
        override val neutral: Color = Color(0xFFF2F2F7)
        override val border: Color = Color(0xFFE5E5EA)
        override val outline: Color = Color(0xFFC7C7CC)
    }
}

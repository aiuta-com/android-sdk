package com.aiuta.fashionsdk.compose.tokens

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

// TODO Docs
@Immutable
public interface AiutaColors {
    public val brand: Color
    public val accent: Color
    public val aiuta: Color
    public val background: Color
    public val backgroundElevation2: Color
    public val primary: Color
    public val secondary: Color
    public val tertiary: Color
    public val onDark: Color
    public val onLight: Color
    public val gray1: Color
    public val gray2: Color
    public val gray3: Color
    public val error: Color
}

public fun defaultAiutaColors(
    brand: Color? = null,
    accent: Color? = null,
    isDarkTheme: Boolean = false,
): AiutaColors {
    if (isDarkTheme) {
        return defaultAiutaDarkColors(
            brand = brand,
            accent = accent,
        )
    } else {
        return defaultAiutaLightColors(
            brand = brand,
            accent = accent,
        )
    }
}

private fun defaultAiutaDarkColors(
    brand: Color? = null,
    accent: Color? = null,
): AiutaColors {
    return object : AiutaColors {
        override val brand: Color = brand ?: Color(0xFFFF6000)
        override val accent: Color = accent ?: Color(0xFF439E49)
        override val aiuta: Color = Color(0xFF4F14FF)
        override val background: Color = Color(0xFF000000)
        override val backgroundElevation2: Color = Color(0xFF1C1C1E)
        override val primary: Color = Color(0xFFFFFFFF)
        override val secondary: Color = Color(0xFF8C8C8C)
        override val tertiary: Color = Color(0x4AEFEEEE)
        override val onDark: Color = Color(0xFFFFFFFF)
        override val onLight: Color = Color(0xFF000000)
        override val gray1: Color = Color(0xFF1D1D1D)
        override val gray2: Color = Color(0xFF2C2C2E)
        override val gray3: Color = Color(0xFF48484A)
        override val error: Color = Color(0xFFE52239)
    }
}

private fun defaultAiutaLightColors(
    brand: Color? = null,
    accent: Color? = null,
): AiutaColors {
    return object : AiutaColors {
        override val brand: Color = brand ?: Color(0xFFFF6000)
        override val accent: Color = accent ?: Color(0xFF439E49)
        override val aiuta: Color = Color(0xFF4000FF)
        override val background: Color = Color(0xFFFFFFFF)
        override val backgroundElevation2: Color = Color(0xFFFFFFFF)
        override val primary: Color = Color(0xFF000000)
        override val secondary: Color = Color(0xFFB2B2B2)
        override val tertiary: Color = Color(0xFFEEEEEE)
        override val onDark: Color = Color(0xFFFFFFFF)
        override val onLight: Color = Color(0xFF000000)
        override val gray1: Color = Color(0xFFF2F2F7)
        override val gray2: Color = Color(0xFFE5E5EA)
        override val gray3: Color = Color(0xFFC7C7CC)
        override val error: Color = Color(0xFFE52239)
    }
}

package com.aiuta.fashionsdk.compose.tokens

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Public interface for implementation of [AiutaColors]
 * for [AiutaTheme]
 *
 * @see AiutaTheme
 */
@Immutable
public interface AiutaColors {
    // DS
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
    public val neutral: Color
    public val neutral2: Color
    public val neutral3: Color
    public val error: Color
}

public fun defaultAiutaColors(
    accent: Color? = null,
    background: Color? = null,
    brand: Color? = null,
    isDarkTheme: Boolean = false,
): AiutaColors {
    if (isDarkTheme) {
        return defaultAiutaDarkColors(
            accent = accent,
            background = background,
            brand = brand,
        )
    } else {
        return defaultAiutaLightColors(
            accent = accent,
            background = background,
            brand = brand,
        )
    }
}

private fun defaultAiutaDarkColors(
    accent: Color? = null,
    background: Color? = null,
    brand: Color? = null,
): AiutaColors {
    return object : AiutaColors {
        override val aiuta: Color = Color(0xFF4F14FF)
        override val error: Color = Color(0xFFE52239)
        override val accent: Color = accent ?: Color(0xFF439E49)
        override val brand: Color = brand ?: this.aiuta
        override val background: Color = background ?: Color(0xFF000000)
        override val backgroundElevation2: Color = Color(0xFF1C1C1E)
        override val primary: Color = Color(0xFFFFFFFF)
        override val secondary: Color = Color(0xFF8C8C8C)
        override val tertiary: Color = Color(0x4AEFEEEE)
        override val onDark: Color = Color(0xFFFFFFFF)
        override val onLight: Color = Color(0xFF000000)
        override val neutral: Color = Color(0xFF1D1D1D)
        override val neutral2: Color = Color(0xFF2C2C2E)
        override val neutral3: Color = Color(0xFF48484A)
    }
}

private fun defaultAiutaLightColors(
    accent: Color? = null,
    background: Color? = null,
    brand: Color? = null,
): AiutaColors {
    return object : AiutaColors {
        override val aiuta: Color = Color(0xFF4000FF)
        override val error: Color = Color(0xFFE52239)
        override val accent: Color = accent ?: this.error
        override val brand: Color = brand ?: this.aiuta
        override val background: Color = background ?: Color(0xFFFFFFFF)
        override val backgroundElevation2: Color = Color(0xFFFFFFFF)
        override val primary: Color = Color(0xFF000000)
        override val secondary: Color = Color(0xFFB2B2B2)
        override val tertiary: Color = Color(0xFFEEEEEE)
        override val onDark: Color = Color(0xFFFFFFFF)
        override val onLight: Color = Color(0xFF000000)
        override val neutral: Color = Color(0xFFF2F2F7)
        override val neutral2: Color = Color(0xFFE5E5EA)
        override val neutral3: Color = Color(0xFFC7C7CC)
    }
}

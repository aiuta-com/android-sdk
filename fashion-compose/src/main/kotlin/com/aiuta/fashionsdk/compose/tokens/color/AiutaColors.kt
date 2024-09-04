package com.aiuta.fashionsdk.compose.tokens.color

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Public interface for implementation of [AiutaColors]
 * for [AiutaTheme]
 *
 * @see AiutaTheme
 */
@Immutable
public class AiutaColors(
    // Text
    public val primary: Color,
    public val secondary: Color,
    public val tertiary: Color,
    public val onDark: Color,
    // Brand
    public val brand: Color,
    public val accent: Color,
    public val error: Color,
    // Aiuta
    public val aiuta: Color,
    // Background
    public val background: Color,
    // Other
    public val neutral: Color,
    public val neutral2: Color,
    public val neutral3: Color,
    // Deprecated
    @Deprecated("Remove with new release SDK")
    public val backgroundElevation2: Color,
    @Deprecated("Remove with new release SDK")
    public val onLight: Color,
)

public val DefaultAiutaColors: AiutaColors by lazy { aiutaLightColors() }

@Deprecated("Use light, dark or custom colors")
public fun aiutaColors(
    accent: Color? = null,
    background: Color? = null,
    brand: Color? = null,
    isDarkTheme: Boolean = false,
): AiutaColors {
    return DefaultAiutaColors
}

package com.aiuta.fashionsdk.compose.tokens.color

import androidx.compose.ui.graphics.Color

/**
 * Implementation of [AiutaColors] for light theme
 *
 * @see AiutaColors
 */
public fun aiutaLightColors(
    primary: Color = Color(0xFF000000),
    secondary: Color = Color(0xFF9F9F9F),
    tertiary: Color = Color(0xFFEEEEEE),
    onDark: Color = Color(0xFFFFFFFF),
    brand: Color = Color(0xFF4000FF),
    accent: Color = Color(0xFFFB1010),
    error: Color = Color(0xFFEF5754),
    aiuta: Color = Color(0xFF4000FF),
    background: Color = Color(0xFFFFFFFF),
    neutral: Color = Color(0xFFF2F2F7),
    neutral2: Color = Color(0xFFE5E5EA),
    neutral3: Color = Color(0xFFC7C7CC),
    // Deprecated
    backgroundElevation2: Color = Color(0xFF1C1C1E),
    onLight: Color = Color(0xFF000000),
): AiutaColors {
    return AiutaColors(
        primary = primary,
        secondary = secondary,
        tertiary = tertiary,
        onDark = onDark,
        brand = brand,
        error = error,
        aiuta = aiuta,
        background = background,
        neutral = neutral,
        neutral2 = neutral2,
        neutral3 = neutral3,
        accent = accent,
        backgroundElevation2 = backgroundElevation2,
        onLight = onLight,
    )
}

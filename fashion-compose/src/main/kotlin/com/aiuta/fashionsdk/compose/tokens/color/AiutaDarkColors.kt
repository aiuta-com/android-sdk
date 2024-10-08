package com.aiuta.fashionsdk.compose.tokens.color

import androidx.compose.ui.graphics.Color

/**
 * Implementation of [AiutaColors] for dark theme
 *
 * @see AiutaColors
 */
public fun aiutaDarkColors(
    primary: Color = Color(0xFF000000),
    secondary: Color = Color(0xFF9F9F9F),
    tertiary: Color = Color(0x4AEEEEEE),
    onDark: Color = Color(0xFFFFFFFF),
    onError: Color = Color(0xFF000000),
    brand: Color = Color(0xFF4000FF),
    accent: Color = Color(0xFFFB1010),
    error: Color = Color(0xFFEF5754),
    aiuta: Color = Color(0xFF4000FF),
    background: Color = Color(0xFF000000),
    neutral: Color = Color(0xFF1D1D1D),
    neutral2: Color = Color(0xFF2C2C2E),
    neutral3: Color = Color(0xFF48484A),
    // Deprecated
    backgroundElevation2: Color = Color(0xFFFFFFFF),
    onLight: Color = Color(0xFF000000),
): AiutaColors {
    return AiutaColors(
        primary = primary,
        secondary = secondary,
        tertiary = tertiary,
        onDark = onDark,
        onError = onError,
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

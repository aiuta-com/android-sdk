package com.aiuta.fashionsdk.compose.tokens.color

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.aiuta.fashionsdk.compose.tokens.AiutaTheme

/**
 * Public class, which contains all colors used in SDK.
 * By default, SDK uses [DefaultAiutaColors], but the host
 * application has the possibility to override it.
 *
 * This class provides a comprehensive set of colors for text, brand elements,
 * backgrounds, and other UI components.
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

/**
 * Default colors for SDK
 *
 * @see AiutaTheme
 * @see AiutaColors
 */
public val DefaultAiutaColors: AiutaColors by lazy { aiutaLightColors() }

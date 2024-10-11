package com.aiuta.fashionsdk.compose.tokens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import com.aiuta.fashionsdk.compose.tokens.color.AiutaColors
import com.aiuta.fashionsdk.compose.tokens.color.DefaultAiutaColors
import com.aiuta.fashionsdk.compose.tokens.gradient.AiutaGradients
import com.aiuta.fashionsdk.compose.tokens.gradient.DefaultAiutaGradients
import com.aiuta.fashionsdk.compose.tokens.icon.AiutaIcons
import com.aiuta.fashionsdk.compose.tokens.images.AiutaImage
import com.aiuta.fashionsdk.compose.tokens.shape.AiutaShapes
import com.aiuta.fashionsdk.compose.tokens.shape.DefaultAiutaShapes
import com.aiuta.fashionsdk.compose.tokens.typography.AiutaTypography
import com.aiuta.fashionsdk.compose.tokens.typography.DefaultAiutaTypography

/**
 * AiutaTheme class holds the design system tokens for the Aiuta application.
 *
 * @property colors The color palette used in the theme.
 * @property gradients The gradient definitions used in the theme.
 * @property typography The typography styles used in the theme.
 * @property icons The icon set used in the theme.
 * @property shapes The shape definitions used in the theme.
 * @property watermark Optional watermark resource.
 *
 * @see AiutaColors
 * @see AiutaGradients
 * @see AiutaTypography
 * @see AiutaIcons
 * @see AiutaShapes
 */
@Immutable
public class AiutaTheme(
    public val colors: AiutaColors,
    public val gradients: AiutaGradients,
    public val typography: AiutaTypography,
    public val icons: AiutaIcons,
    public val shapes: AiutaShapes,
    public val watermark: AiutaImage?,
)

/**
 * Creates an instance of [AiutaTheme]
 *
 * @return An instance of [AiutaTheme]
 */
public fun aiutaTheme(
    icons: AiutaIcons,
    colors: AiutaColors = DefaultAiutaColors,
    gradients: AiutaGradients = DefaultAiutaGradients,
    typography: AiutaTypography = DefaultAiutaTypography,
    shapes: AiutaShapes = DefaultAiutaShapes,
    watermark: AiutaImage? = null,
): AiutaTheme {
    return AiutaTheme(
        icons = icons,
        colors = colors,
        gradients = gradients,
        typography = typography,
        shapes = shapes,
        watermark = watermark,
    )
}

/**
 * Composable function to create an instance of [AiutaTheme] with composable remember.
 *
 * @return An instance of [AiutaTheme].
 */
@Composable
public fun rememberAiutaTheme(
    icons: AiutaIcons,
    colors: AiutaColors = DefaultAiutaColors,
    gradients: AiutaGradients = DefaultAiutaGradients,
    typography: AiutaTypography = DefaultAiutaTypography,
    shapes: AiutaShapes = DefaultAiutaShapes,
    watermark: AiutaImage? = null,
): AiutaTheme {
    return remember(
        icons,
        colors,
        gradients,
        typography,
        shapes,
        watermark,
    ) {
        aiutaTheme(
            icons = icons,
            colors = colors,
            gradients = gradients,
            typography = typography,
            shapes = shapes,
            watermark = watermark,
        )
    }
}

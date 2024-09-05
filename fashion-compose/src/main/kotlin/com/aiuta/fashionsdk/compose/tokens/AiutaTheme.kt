package com.aiuta.fashionsdk.compose.tokens

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.compose.tokens.color.AiutaColors
import com.aiuta.fashionsdk.compose.tokens.color.DefaultAiutaColors
import com.aiuta.fashionsdk.compose.tokens.gradient.AiutaGradients
import com.aiuta.fashionsdk.compose.tokens.gradient.DefaultAiutaGradients
import com.aiuta.fashionsdk.compose.tokens.icon.AiutaIcons
import com.aiuta.fashionsdk.compose.tokens.shape.AiutaShapes
import com.aiuta.fashionsdk.compose.tokens.shape.DefaultAiutaShapes
import com.aiuta.fashionsdk.compose.tokens.typography.AiutaTypography
import com.aiuta.fashionsdk.compose.tokens.typography.DefaultAiutaTypography

/**
 * Final theme for all UI flows
 */
@Immutable
public class AiutaTheme(
    @Deprecated("Should delete in new release")
    public val navBarTheme: AiutaNavBarTheme,
    public val colors: AiutaColors,
    public val gradients: AiutaGradients,
    public val typography: AiutaTypography,
    public val icons: AiutaIcons,
    public val shapes: AiutaShapes,
    @get:DrawableRes
    public val watermarkRes: Int?,
)

public fun aiutaTheme(
    icons: AiutaIcons,
    colors: AiutaColors = DefaultAiutaColors,
    gradients: AiutaGradients = DefaultAiutaGradients,
    typography: AiutaTypography = DefaultAiutaTypography,
    shapes: AiutaShapes = DefaultAiutaShapes,
    @DrawableRes watermarkRes: Int? = null,
): AiutaTheme {
    return AiutaTheme(
        icons = icons,
        colors = colors,
        gradients = gradients,
        typography = typography,
        shapes = shapes,
        watermarkRes = watermarkRes,
        navBarTheme = defaultAiutaNavBarTheme(),
    )
}

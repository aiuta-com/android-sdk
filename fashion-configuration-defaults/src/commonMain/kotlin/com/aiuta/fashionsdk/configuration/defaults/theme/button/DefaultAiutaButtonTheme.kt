package com.aiuta.fashionsdk.configuration.defaults.theme.button

import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.button.button
import com.aiuta.fashionsdk.configuration.ui.theme.button.shapes.AiutaButtonThemeShapes
import com.aiuta.fashionsdk.configuration.ui.theme.button.typography.AiutaButtonThemeTypography

/**
 * Configures the default button theme for the Aiuta SDK.
 *
 * This function sets up the button with default typography and shapes.
 *
 * @return The updated [AiutaTheme.Builder] instance.
 */
public fun AiutaTheme.Builder.defaultButton(): AiutaTheme.Builder = button {
    typography = AiutaButtonThemeTypography.Default()
    shapes = AiutaButtonThemeShapes.Default()
}

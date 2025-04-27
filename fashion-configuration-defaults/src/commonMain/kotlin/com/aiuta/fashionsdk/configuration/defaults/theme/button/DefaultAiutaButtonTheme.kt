package com.aiuta.fashionsdk.configuration.defaults.theme.button

import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.button.button
import com.aiuta.fashionsdk.configuration.ui.theme.button.shapes.AiutaButtonThemeShapes
import com.aiuta.fashionsdk.configuration.ui.theme.button.typography.AiutaButtonThemeTypography

public fun AiutaTheme.Builder.defaultButton(): AiutaTheme.Builder = button {
    typography = AiutaButtonThemeTypography.Default()
    shapes = AiutaButtonThemeShapes.Default()
}

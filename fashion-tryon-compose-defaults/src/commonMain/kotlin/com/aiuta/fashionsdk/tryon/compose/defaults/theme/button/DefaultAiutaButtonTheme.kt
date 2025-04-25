package com.aiuta.fashionsdk.tryon.compose.defaults.theme.button

import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.button.button
import com.aiuta.fashionsdk.configuration.ui.theme.button.shapes.AiutaButtonThemeShapes
import com.aiuta.fashionsdk.configuration.ui.theme.button.typography.AiutaButtonThemeTypography

public fun AiutaTheme.Builder.defaultButton() {
    button {
        typography = AiutaButtonThemeTypography.Default()
        shapes = AiutaButtonThemeShapes.Default()
    }
}

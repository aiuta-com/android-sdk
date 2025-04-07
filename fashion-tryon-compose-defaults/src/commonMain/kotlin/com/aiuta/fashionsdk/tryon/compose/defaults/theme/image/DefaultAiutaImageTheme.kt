package com.aiuta.fashionsdk.tryon.compose.defaults.theme.image

import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.image.image
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.image.shapes.AiutaImageThemeShapes
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.theme.image.DefaultAiutaImageThemeIcons

public fun AiutaTheme.Builder.defaultImage() {
    image {
        shapes = AiutaImageThemeShapes.Default()
        icons = DefaultAiutaImageThemeIcons()
    }
}

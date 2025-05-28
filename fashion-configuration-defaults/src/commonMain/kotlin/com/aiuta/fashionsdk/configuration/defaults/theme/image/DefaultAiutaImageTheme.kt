package com.aiuta.fashionsdk.configuration.defaults.theme.image

import com.aiuta.fashionsdk.configuration.defaults.icons.theme.image.DefaultAiutaImageThemeIcons
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.image.image
import com.aiuta.fashionsdk.configuration.ui.theme.image.shapes.AiutaImageThemeShapes

/**
 * Configures the default image theme for the Aiuta SDK.
 *
 * This function sets up the image with default shapes and icons.
 *
 * @return The updated [AiutaTheme.Builder] instance.
 */
public fun AiutaTheme.Builder.defaultImage(): AiutaTheme.Builder = image {
    shapes = AiutaImageThemeShapes.Default()
    icons = DefaultAiutaImageThemeIcons()
}

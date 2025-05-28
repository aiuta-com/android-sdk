package com.aiuta.fashionsdk.configuration.defaults.theme.label

import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.label.label
import com.aiuta.fashionsdk.configuration.ui.theme.label.typography.AiutaLabelThemeTypography

/**
 * Configures the default label theme for the Aiuta SDK.
 *
 * This function sets up the label with default typography.
 *
 * @return The updated [AiutaTheme.Builder] instance.
 */
public fun AiutaTheme.Builder.defaultLabel(): AiutaTheme.Builder = label {
    typography = AiutaLabelThemeTypography.Default()
}

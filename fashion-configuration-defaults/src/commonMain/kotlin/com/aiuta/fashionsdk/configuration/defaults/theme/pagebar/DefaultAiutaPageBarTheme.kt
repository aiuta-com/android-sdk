package com.aiuta.fashionsdk.configuration.defaults.theme.pagebar

import com.aiuta.fashionsdk.configuration.defaults.icons.theme.pagebar.DefaultAiutaPageBarThemeIcons
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.pagebar.pageBar
import com.aiuta.fashionsdk.configuration.ui.theme.pagebar.toggles.AiutaPageBarThemeToggles
import com.aiuta.fashionsdk.configuration.ui.theme.pagebar.typography.AiutaPageBarThemeTypography

/**
 * Configures the default page bar theme for the Aiuta SDK.
 *
 * This function sets up the page bar with default typography, icons, and toggles.
 *
 * @return The updated [AiutaTheme.Builder] instance.
 */
public fun AiutaTheme.Builder.defaultPageBar(): AiutaTheme.Builder = pageBar {
    typography = AiutaPageBarThemeTypography.Default()
    icons = DefaultAiutaPageBarThemeIcons()
    toggles = AiutaPageBarThemeToggles.Default()
}

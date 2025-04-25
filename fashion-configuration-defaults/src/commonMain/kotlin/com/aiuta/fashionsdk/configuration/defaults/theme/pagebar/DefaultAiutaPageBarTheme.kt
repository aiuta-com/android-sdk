package com.aiuta.fashionsdk.configuration.defaults.theme.pagebar

import com.aiuta.fashionsdk.configuration.defaults.icons.theme.pagebar.DefaultAiutaPageBarThemeIcons
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.pagebar.pageBar
import com.aiuta.fashionsdk.configuration.ui.theme.pagebar.toggles.AiutaPageBarThemeToggles
import com.aiuta.fashionsdk.configuration.ui.theme.pagebar.typography.AiutaPageBarThemeTypography

public fun AiutaTheme.Builder.defaultPageBar() {
    pageBar {
        typography = AiutaPageBarThemeTypography.Default()
        icons = DefaultAiutaPageBarThemeIcons()
        toggles = AiutaPageBarThemeToggles.Default()
    }
}

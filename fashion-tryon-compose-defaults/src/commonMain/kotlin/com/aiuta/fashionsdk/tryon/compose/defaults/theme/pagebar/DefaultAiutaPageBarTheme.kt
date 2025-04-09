package com.aiuta.fashionsdk.tryon.compose.defaults.theme.pagebar

import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.pagebar.pageBar
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.pagebar.toggles.AiutaPageBarThemeToggles
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.pagebar.typography.AiutaPageBarThemeTypography
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.theme.pagebar.DefaultAiutaPageBarThemeIcons

public fun AiutaTheme.Builder.defaultPageBar() {
    pageBar {
        typography = AiutaPageBarThemeTypography.Default()
        icons = DefaultAiutaPageBarThemeIcons()
        toggles = AiutaPageBarThemeToggles.Default()
    }
}

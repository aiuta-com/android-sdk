package com.aiuta.fashionsdk.configuration.defaults.theme.productbar

import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.colors.AiutaProductBarThemeColors
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.productBar
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.toggles.AiutaProductBarThemeToggles
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.typography.AiutaProductBarThemeTypography

public fun AiutaTheme.Builder.defaultProductBar() {
    productBar {
        typography = AiutaProductBarThemeTypography.Default()
        colors = AiutaProductBarThemeColors.Default()
        toggles = AiutaProductBarThemeToggles.Default()
    }
}

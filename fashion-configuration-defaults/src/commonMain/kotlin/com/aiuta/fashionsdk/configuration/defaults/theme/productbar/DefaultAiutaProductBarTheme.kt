package com.aiuta.fashionsdk.configuration.defaults.theme.productbar

import com.aiuta.fashionsdk.configuration.defaults.icons.theme.productbar.DefaultAiutaProductBarThemeIcons
import com.aiuta.fashionsdk.configuration.defaults.theme.productbar.prices.defaultPrices
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.productBar
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.toggles.AiutaProductBarThemeToggles
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.typography.AiutaProductBarThemeTypography

public fun AiutaTheme.Builder.defaultProductBar() {
    productBar {
        defaultPrices()
        typography = AiutaProductBarThemeTypography.Default()
        toggles = AiutaProductBarThemeToggles.Default()
        icons = DefaultAiutaProductBarThemeIcons()
    }
}

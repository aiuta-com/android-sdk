package com.aiuta.fashionsdk.configuration.defaults.theme.productbar

import com.aiuta.fashionsdk.configuration.defaults.icons.theme.productbar.DefaultAiutaProductBarThemeIcons
import com.aiuta.fashionsdk.configuration.defaults.theme.productbar.prices.defaultPrices
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.productBar
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.toggles.AiutaProductBarThemeToggles
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.typography.AiutaProductBarThemeTypography

/**
 * Configures the default product bar theme for the Aiuta SDK.
 *
 * This function sets up the product bar with default prices, typography, toggles, and icons.
 *
 * @return The updated [AiutaTheme.Builder] instance.
 */
public fun AiutaTheme.Builder.defaultProductBar(): AiutaTheme.Builder = productBar {
    defaultPrices()
    typography = AiutaProductBarThemeTypography.Default()
    toggles = AiutaProductBarThemeToggles.Default()
    icons = DefaultAiutaProductBarThemeIcons()
}

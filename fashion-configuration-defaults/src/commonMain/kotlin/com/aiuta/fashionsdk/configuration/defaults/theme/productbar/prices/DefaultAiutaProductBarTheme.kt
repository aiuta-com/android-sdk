package com.aiuta.fashionsdk.configuration.defaults.theme.productbar.prices

import com.aiuta.fashionsdk.configuration.ui.theme.productbar.AiutaProductBarTheme
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.prices.colors.AiutaProductBarPricesThemeColors
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.prices.prices
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.prices.typography.AiutaProductBarPricesThemeTypography

public fun AiutaProductBarTheme.Builder.defaultPrices() {
    prices {
        typography = AiutaProductBarPricesThemeTypography.Default()
        colors = AiutaProductBarPricesThemeColors.Default()
    }
}

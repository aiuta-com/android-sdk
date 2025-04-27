package com.aiuta.fashionsdk.configuration.ui.theme.productbar

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.icons.AiutaProductBarThemeIcons
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.prices.AiutaProductBarPricesTheme
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.toggles.AiutaProductBarThemeToggles
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.typography.AiutaProductBarThemeTypography

@Immutable
public class AiutaProductBarTheme private constructor(
    // Themes
    public val prices: AiutaProductBarPricesTheme?,
    // General
    public val typography: AiutaProductBarThemeTypography,
    public val toggles: AiutaProductBarThemeToggles,
    public val icons: AiutaProductBarThemeIcons,
) {
    public class Builder {
        public var prices: AiutaProductBarPricesTheme? = null
        public var typography: AiutaProductBarThemeTypography? = null
        public var toggles: AiutaProductBarThemeToggles? = null
        public var icons: AiutaProductBarThemeIcons? = null

        public fun build(): AiutaProductBarTheme {
            val parentClass = "AiutaProductBarTheme"

            return AiutaProductBarTheme(
                prices = prices,
                typography = typography.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "typography",
                ),
                toggles = toggles.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "toggles",
                ),
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
                ),
            )
        }
    }
}

public inline fun AiutaTheme.Builder.productBar(
    block: AiutaProductBarTheme.Builder.() -> Unit,
): AiutaTheme.Builder = apply {
    productBar = AiutaProductBarTheme.Builder().apply(block).build()
}

package com.aiuta.fashionsdk.configuration.ui.theme.productbar.prices

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.AiutaProductBarTheme
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.prices.colors.AiutaProductBarPricesThemeColors
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.prices.typography.AiutaProductBarPricesThemeTypography

@Immutable
public class AiutaProductBarPricesTheme private constructor(
    public val typography: AiutaProductBarPricesThemeTypography,
    public val colors: AiutaProductBarPricesThemeColors,
) {
    public class Builder {
        public var typography: AiutaProductBarPricesThemeTypography? = null
        public var colors: AiutaProductBarPricesThemeColors? = null

        public fun build(): AiutaProductBarPricesTheme {
            val parentClass = "AiutaProductBarPricesTheme"

            return AiutaProductBarPricesTheme(
                typography = typography.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "typography",
                ),
                colors = colors.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "colors",
                ),
            )
        }
    }
}

public inline fun AiutaProductBarTheme.Builder.prices(
    block: AiutaProductBarPricesTheme.Builder.() -> Unit,
): AiutaProductBarTheme.Builder = apply {
    prices = AiutaProductBarPricesTheme.Builder().apply(block).build()
}

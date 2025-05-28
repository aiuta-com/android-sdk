package com.aiuta.fashionsdk.configuration.ui.theme.productbar.prices

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.AiutaProductBarTheme
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.prices.colors.AiutaProductBarPricesThemeColors
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.prices.typography.AiutaProductBarPricesThemeTypography

/**
 * Price display theme configuration for product bars.
 * 
 * This immutable class defines the visual styling for price displays in product
 * bars throughout the SDK, including typography and color configurations. It
 * provides a consistent appearance for price-related UI elements that can be
 * customized to match your app's design system.
 * 
 * 
 * @property typography Typography configuration for price text
 * @property colors Color configuration for price elements
 * @see AiutaProductBarPricesThemeTypography
 * @see AiutaProductBarPricesThemeColors
 */
@Immutable
public class AiutaProductBarPricesTheme(
    public val typography: AiutaProductBarPricesThemeTypography,
    public val colors: AiutaProductBarPricesThemeColors,
) {
    /**
     * Builder class for creating [AiutaProductBarPricesTheme] instances.
     * 
     * This builder ensures all required theme components are set before creating
     * the final price theme configuration.
     */
    public class Builder {
        /**
         * Typography configuration for price text.
         */
        public var typography: AiutaProductBarPricesThemeTypography? = null

        /**
         * Color configuration for price elements.
         */
        public var colors: AiutaProductBarPricesThemeColors? = null

        /**
         * Creates an [AiutaProductBarPricesTheme] instance with the configured properties.
         * 
         * @return Configured [AiutaProductBarPricesTheme] instance
         * @throws IllegalArgumentException if required properties are not set
         */
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

/**
 * Extension function for configuring price theme within a [AiutaProductBarTheme.Builder].
 * 
 * This extension provides a convenient DSL for configuring the price theme
 * as part of the product bar theme setup.
 * 
 * ```kotlin
 * theme {
 *     productBar {
 *         prices {
 *             typography = AiutaProductBarPricesThemeTypography.Default()
 *             colors = AiutaProductBarPricesThemeColors.Default()
 *         }
 *     }
 * }
 * ```
 * 
 * @param block Configuration block for setting up the price theme
 * @return The product bar theme builder for method chaining
 * @see AiutaProductBarTheme.Builder
 * @see AiutaProductBarPricesTheme.Builder
 */
public inline fun AiutaProductBarTheme.Builder.prices(
    block: AiutaProductBarPricesTheme.Builder.() -> Unit,
): AiutaProductBarTheme.Builder = apply {
    prices = AiutaProductBarPricesTheme.Builder().apply(block).build()
}

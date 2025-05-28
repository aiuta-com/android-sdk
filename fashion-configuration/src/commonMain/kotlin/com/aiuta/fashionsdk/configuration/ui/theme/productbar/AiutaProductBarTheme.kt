package com.aiuta.fashionsdk.configuration.ui.theme.productbar

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.icons.AiutaProductBarThemeIcons
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.prices.AiutaProductBarPricesTheme
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.toggles.AiutaProductBarThemeToggles
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.typography.AiutaProductBarThemeTypography

/**
 * Product bar theme configuration for the Aiuta SDK.
 * 
 * This class defines the visual styling for product information bars in the SDK,
 * including price displays, typography, toggle controls, and icons. It provides
 * a consistent appearance for product-related UI elements that can be customized
 * to match your app's design.
 * 
 * ```kotlin
 * theme {
 *     productBar {
 *         prices = AiutaProductBarPricesTheme.Default()
 *         typography = AiutaProductBarThemeTypography.Default()
 *         toggles = AiutaProductBarThemeToggles.Default()
 *         icons = AiutaProductBarThemeIcons.Default()
 *     }
 * }
 * ```
 * 
 * @property prices Theme configuration for price displays
 * @property typography Typography configuration for text elements
 * @property toggles Theme configuration for toggle controls
 * @property icons Theme configuration for product bar icons
 * @see AiutaProductBarPricesTheme
 * @see AiutaProductBarThemeTypography
 * @see AiutaProductBarThemeToggles
 * @see AiutaProductBarThemeIcons
 */
@Immutable
public class AiutaProductBarTheme(
    // Themes
    public val prices: AiutaProductBarPricesTheme?,
    // General
    public val typography: AiutaProductBarThemeTypography,
    public val toggles: AiutaProductBarThemeToggles,
    public val icons: AiutaProductBarThemeIcons,
) {
    /**
     * Builder class for creating [AiutaProductBarTheme] instances.
     * 
     * This builder ensures all required theme components are set before creating
     * the final product bar theme configuration.
     */
    public class Builder {
        /**
         * Theme configuration for price displays in the product bar.
         */
        public var prices: AiutaProductBarPricesTheme? = null

        /**
         * Typography configuration for text elements in the product bar.
         */
        public var typography: AiutaProductBarThemeTypography? = null

        /**
         * Theme configuration for toggle controls in the product bar.
         */
        public var toggles: AiutaProductBarThemeToggles? = null

        /**
         * Theme configuration for icons used in the product bar.
         */
        public var icons: AiutaProductBarThemeIcons? = null

        /**
         * Creates an [AiutaProductBarTheme] instance with the configured properties.
         * 
         * @return Configured [AiutaProductBarTheme] instance
         * @throws IllegalArgumentException if required properties are not set
         */
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
                    property = "styles",
                ),
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
                ),
            )
        }
    }
}

/**
 * Extension function for configuring product bar theme within an [AiutaTheme.Builder].
 * 
 * This extension provides a convenient DSL for configuring the product bar theme
 * as part of the main theme setup.
 * 
 * ```kotlin
 * theme {
 *     productBar {
 *         prices = AiutaProductBarPricesTheme.Default()
 *         typography = AiutaProductBarThemeTypography.Default()
 *         toggles = AiutaProductBarThemeToggles.Default()
 *         icons = AiutaProductBarThemeIcons.Default()
 *     }
 *     // Configure other theme components...
 * }
 * ```
 * 
 * @param block Configuration block for setting up the product bar theme
 * @return The theme builder for method chaining
 * @see AiutaTheme.Builder
 * @see AiutaProductBarTheme.Builder
 */
public inline fun AiutaTheme.Builder.productBar(
    block: AiutaProductBarTheme.Builder.() -> Unit,
): AiutaTheme.Builder = apply {
    productBar = AiutaProductBarTheme.Builder().apply(block).build()
}

package com.aiuta.fashionsdk.configuration.ui.theme.productbar.prices.colors

import androidx.compose.ui.graphics.Color

/**
 * Color configuration for price displays in product bars.
 * 
 * This interface defines the colors used in price displays throughout the SDK.
 * It provides consistent price styling that can be customized to match your
 * app's design system.
 * 
 * 
 * @property discountedPrice Color used for displaying discounted prices
 */
public interface AiutaProductBarPricesThemeColors {
    public val discountedPrice: Color

    /**
     * Default implementation of [AiutaProductBarPricesThemeColors].
     * 
     * This class provides standard colors for price displays:
     * - Discounted price: Red (#FB1010)
     * 
     * ```kotlin
     * theme {
     *     productBar {
     *         prices {
     *             colors = AiutaProductBarPricesThemeColors.Default()
     *         }
     *     }
     * }
     * ```
     */
    public class Default : AiutaProductBarPricesThemeColors {
        override val discountedPrice: Color = Color(0xFFFB1010)
    }
}

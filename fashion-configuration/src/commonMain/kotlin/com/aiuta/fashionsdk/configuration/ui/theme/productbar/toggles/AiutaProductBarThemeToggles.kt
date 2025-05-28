package com.aiuta.fashionsdk.configuration.ui.theme.productbar.toggles

/**
 * Behavior toggle configuration for product bars.
 *
 * This interface defines behavior toggles that control the layout and appearance
 * of product bars throughout the SDK. It provides consistent behavior settings
 * that can be customized to match your app's design requirements.
 *
 *
 * @property applyProductFirstImageExtraPadding Whether to apply extra padding to the first product image
 */
public interface AiutaProductBarThemeToggles {
    public val applyProductFirstImageExtraPadding: Boolean

    /**
     * Default implementation of [AiutaProductBarThemeToggles].
     *
     * This class provides standard behavior settings for product bars:
     * - Product first image extra padding: Disabled
     *
     * ```kotlin
     * theme {
     *     productBar {
     *         toggles = AiutaProductBarThemeToggles.Default()
     *     }
     * }
     * ```
     */
    public class Default : AiutaProductBarThemeToggles {
        override val applyProductFirstImageExtraPadding: Boolean = false
    }
}

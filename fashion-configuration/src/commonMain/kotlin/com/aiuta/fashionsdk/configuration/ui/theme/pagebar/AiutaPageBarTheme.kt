package com.aiuta.fashionsdk.configuration.ui.theme.pagebar

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.pagebar.icons.AiutaPageBarThemeIcons
import com.aiuta.fashionsdk.configuration.ui.theme.pagebar.toggles.AiutaPageBarThemeToggles
import com.aiuta.fashionsdk.configuration.ui.theme.pagebar.typography.AiutaPageBarThemeTypography

/**
 * Page bar theme configuration for the Aiuta SDK.
 * 
 * This immutable class defines the visual styling for page navigation bars throughout
 * the SDK, including typography, icons, and toggle configurations. It provides a
 * consistent page bar appearance that can be customized to match your app's design system.
 * 
 * 
 * @property typography Typography configuration for page bar text
 * @property icons Icon configuration for page bar navigation elements
 * @property toggles Configuration for page bar behavior toggles
 * @see AiutaPageBarThemeTypography
 * @see AiutaPageBarThemeIcons
 * @see AiutaPageBarThemeToggles
 */
@Immutable
public class AiutaPageBarTheme(
    public val typography: AiutaPageBarThemeTypography,
    public val icons: AiutaPageBarThemeIcons,
    public val toggles: AiutaPageBarThemeToggles,
) {
    /**
     * Builder class for creating [AiutaPageBarTheme] instances.
     * 
     * This builder ensures all required theme components are set before creating
     * the final page bar theme configuration.
     */
    public class Builder {
        /**
         * Typography configuration for page bar text.
         */
        public var typography: AiutaPageBarThemeTypography? = null

        /**
         * Icon configuration for page bar navigation elements.
         */
        public var icons: AiutaPageBarThemeIcons? = null

        /**
         * Configuration for page bar behavior toggles.
         */
        public var toggles: AiutaPageBarThemeToggles? = null

        /**
         * Creates an [AiutaPageBarTheme] instance with the configured properties.
         * 
         * @return Configured [AiutaPageBarTheme] instance
         * @throws IllegalArgumentException if required properties are not set
         */
        public fun build(): AiutaPageBarTheme {
            val parentClass = "AiutaPageBarTheme"

            return AiutaPageBarTheme(
                typography = typography.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "typography",
                ),
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
                ),
                toggles = toggles.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "styles",
                ),
            )
        }
    }
}

/**
 * Extension function for configuring page bar theme within an [AiutaTheme.Builder].
 * 
 * This extension provides a convenient DSL for configuring the page bar theme
 * as part of the main theme setup.
 * 
 * ```kotlin
 * theme {
 *     pageBar {
 *         typography = AiutaPageBarThemeTypography.Default()
 *         icons = AiutaPageBarThemeIcons.Default()
 *         toggles = AiutaPageBarThemeToggles.Default()
 *     }
 *     // Configure other theme components...
 * }
 * ```
 * 
 * @param block Configuration block for setting up the page bar theme
 * @return The theme builder for method chaining
 * @see AiutaTheme.Builder
 * @see AiutaPageBarTheme.Builder
 */
public inline fun AiutaTheme.Builder.pageBar(
    block: AiutaPageBarTheme.Builder.() -> Unit,
): AiutaTheme.Builder = apply {
    pageBar = AiutaPageBarTheme.Builder().apply(block).build()
}

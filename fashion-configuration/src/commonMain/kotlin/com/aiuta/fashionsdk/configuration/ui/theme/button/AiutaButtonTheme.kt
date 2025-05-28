package com.aiuta.fashionsdk.configuration.ui.theme.button

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.button.shapes.AiutaButtonThemeShapes
import com.aiuta.fashionsdk.configuration.ui.theme.button.typography.AiutaButtonThemeTypography

/**
 * Button theme configuration for the Aiuta SDK.
 *
 * This immutable class defines the visual styling for buttons throughout the SDK,
 * including typography and shape configurations. It provides a consistent button
 * appearance that can be customized to match your app's design system.
 *
 *
 * @property typography Typography configuration for button text
 * @property shapes Shape configuration for button containers
 * @see AiutaButtonThemeTypography
 * @see AiutaButtonThemeShapes
 */
@Immutable
public class AiutaButtonTheme(
    public val typography: AiutaButtonThemeTypography,
    public val shapes: AiutaButtonThemeShapes,
) {
    /**
     * Builder class for creating [AiutaButtonTheme] instances.
     *
     * This builder ensures all required theme components are set before creating
     * the final button theme configuration.
     */
    public class Builder {
        /**
         * Typography configuration for button text.
         */
        public var typography: AiutaButtonThemeTypography? = null

        /**
         * Shape configuration for button containers.
         */
        public var shapes: AiutaButtonThemeShapes? = null

        /**
         * Creates an [AiutaButtonTheme] instance with the configured properties.
         *
         * @return Configured [AiutaButtonTheme] instance
         * @throws IllegalArgumentException if required properties are not set
         */
        public fun build(): AiutaButtonTheme {
            val parentClass = "AiutaButtonTheme"

            return AiutaButtonTheme(
                typography = typography.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "typography",
                ),
                shapes = shapes.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "shapes",
                ),
            )
        }
    }
}

/**
 * Extension function for configuring button theme within an [AiutaTheme.Builder].
 *
 * This extension provides a convenient DSL for configuring the button theme
 * as part of the main theme setup.
 *
 * ```kotlin
 * theme {
 *     button {
 *         typography = AiutaButtonThemeTypography.Default()
 *         shapes = AiutaButtonThemeShapes.Default()
 *     }
 *     // Configure other theme components...
 * }
 * ```
 *
 * @param block Configuration block for setting up the button theme
 * @return The theme builder for method chaining
 * @see AiutaTheme.Builder
 * @see AiutaButtonTheme.Builder
 */
public inline fun AiutaTheme.Builder.button(
    block: AiutaButtonTheme.Builder.() -> Unit,
): AiutaTheme.Builder = apply {
    button = AiutaButtonTheme.Builder().apply(block).build()
}

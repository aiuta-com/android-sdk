package com.aiuta.fashionsdk.configuration.ui.theme.label

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.label.typography.AiutaLabelThemeTypography

/**
 * Label theme configuration for the Aiuta SDK.
 *
 * This immutable class defines the visual styling for text labels throughout
 * the SDK, including typography configurations. It provides a consistent label
 * appearance that can be customized to match your app's design system.
 *
 *
 * @property typography Typography configuration for label text
 * @see AiutaLabelThemeTypography
 */
@Immutable
public class AiutaLabelTheme(
    public val typography: AiutaLabelThemeTypography,
) {
    /**
     * Builder class for creating [AiutaLabelTheme] instances.
     *
     * This builder ensures all required theme components are set before creating
     * the final label theme configuration.
     */
    public class Builder {
        /**
         * Typography configuration for label text.
         */
        public var typography: AiutaLabelThemeTypography? = null

        /**
         * Creates an [AiutaLabelTheme] instance with the configured properties.
         *
         * @return Configured [AiutaLabelTheme] instance
         * @throws IllegalArgumentException if required properties are not set
         */
        public fun build(): AiutaLabelTheme {
            val parentClass = "AiutaLabelTheme"

            return AiutaLabelTheme(
                typography = typography.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "typography",
                ),
            )
        }
    }
}

/**
 * Extension function for configuring label theme within an [AiutaTheme.Builder].
 *
 * This extension provides a convenient DSL for configuring the label theme
 * as part of the main theme setup.
 *
 * ```kotlin
 * theme {
 *     label {
 *         typography = AiutaLabelThemeTypography.Default()
 *     }
 * }
 * ```
 *
 * @param block Configuration block for setting up the label theme
 * @return The theme builder for method chaining
 * @see AiutaTheme.Builder
 * @see AiutaLabelTheme.Builder
 */
public inline fun AiutaTheme.Builder.label(
    block: AiutaLabelTheme.Builder.() -> Unit,
): AiutaTheme.Builder = apply {
    label = AiutaLabelTheme.Builder().apply(block).build()
}

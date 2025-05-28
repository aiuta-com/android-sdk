package com.aiuta.fashionsdk.configuration.ui.theme.error

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.error.colors.AiutaErrorSnackbarThemeColors
import com.aiuta.fashionsdk.configuration.ui.theme.error.icons.AiutaErrorSnackbarThemeIcons
import com.aiuta.fashionsdk.configuration.ui.theme.error.strings.AiutaErrorSnackbarThemeStrings

/**
 * Error snackbar theme configuration for the Aiuta SDK.
 * 
 * This class defines the visual styling for error-related snackbar notifications
 * in the SDK, including text content, icons, and colors. It provides a consistent
 * appearance for error feedback messages that can be customized to match your
 * app's design.
 * 
 * 
 * @property strings Text content configuration for the error snackbar
 * @property icons Theme configuration for icons used in the error snackbar
 * @property colors Color configuration for the error snackbar elements
 * @see AiutaErrorSnackbarThemeStrings
 * @see AiutaErrorSnackbarThemeIcons
 * @see AiutaErrorSnackbarThemeColors
 */
@Immutable
public class AiutaErrorSnackbarTheme(
    public val strings: AiutaErrorSnackbarThemeStrings,
    public val icons: AiutaErrorSnackbarThemeIcons,
    public val colors: AiutaErrorSnackbarThemeColors,
) {
    /**
     * Builder class for creating [AiutaErrorSnackbarTheme] instances.
     * 
     * This builder ensures all required theme components are set before creating
     * the final error snackbar theme configuration.
     */
    public class Builder {
        /**
         * Text content configuration for the error snackbar.
         */
        public var strings: AiutaErrorSnackbarThemeStrings? = null

        /**
         * Theme configuration for icons used in the error snackbar.
         */
        public var icons: AiutaErrorSnackbarThemeIcons? = null

        /**
         * Color configuration for the error snackbar elements.
         */
        public var colors: AiutaErrorSnackbarThemeColors? = null

        /**
         * Creates an [AiutaErrorSnackbarTheme] instance with the configured properties.
         * 
         * @return Configured [AiutaErrorSnackbarTheme] instance
         * @throws IllegalArgumentException if required properties are not set
         */
        public fun build(): AiutaErrorSnackbarTheme {
            val parentClass = "AiutaErrorSnackbarTheme"

            return AiutaErrorSnackbarTheme(
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
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
 * Extension function for configuring error snackbar theme within an [AiutaTheme.Builder].
 * 
 * This extension provides a convenient DSL for configuring the error snackbar theme
 * as part of the main theme setup.
 * 
 * ```kotlin
 * theme {
 *     errorSnackbar {
 *         strings = AiutaErrorSnackbarThemeStrings.Default()
 *         icons = AiutaErrorSnackbarThemeIcons.Default()
 *         colors = AiutaErrorSnackbarThemeColors.Default()
 *     }
 *     // Configure other theme components...
 * }
 * ```
 * 
 * @param block Configuration block for setting up the error snackbar theme
 * @return The theme builder for method chaining
 * @see AiutaTheme.Builder
 * @see AiutaErrorSnackbarTheme.Builder
 */
public inline fun AiutaTheme.Builder.errorSnackbar(
    block: AiutaErrorSnackbarTheme.Builder.() -> Unit,
): AiutaTheme.Builder = apply {
    errorSnackbar = AiutaErrorSnackbarTheme.Builder().apply(block).build()
}

package com.aiuta.fashionsdk.configuration.ui.theme.selection

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.selection.colors.AiutaSelectionSnackbarThemeColors
import com.aiuta.fashionsdk.configuration.ui.theme.selection.icons.AiutaSelectionSnackbarThemeIcons
import com.aiuta.fashionsdk.configuration.ui.theme.selection.strings.AiutaSelectionSnackbarThemeStrings

/**
 * Selection snackbar theme configuration for the Aiuta SDK.
 *
 * This class defines the visual styling for selection-related snackbar
 * in the SDK, including text content, icons, and colors. It provides a consistent
 * appearance for selection feedback messages that can be customized to match your
 * app's design.
 *
 *
 * @property strings Text content configuration for the selection snackbar
 * @property icons Theme configuration for icons used in the selection snackbar
 * @property colors Color configuration for the selection snackbar elements
 * @see AiutaSelectionSnackbarThemeStrings
 * @see AiutaSelectionSnackbarThemeIcons
 * @see AiutaSelectionSnackbarThemeColors
 */
@Immutable
public class AiutaSelectionSnackbarTheme(
    public val strings: AiutaSelectionSnackbarThemeStrings,
    public val icons: AiutaSelectionSnackbarThemeIcons,
    public val colors: AiutaSelectionSnackbarThemeColors,
) {
    /**
     * Builder class for creating [AiutaSelectionSnackbarTheme] instances.
     *
     * This builder ensures all required theme components are set before creating
     * the final selection snackbar theme configuration.
     */
    public class Builder {
        /**
         * Text content configuration for the selection snackbar.
         */
        public var strings: AiutaSelectionSnackbarThemeStrings? = null

        /**
         * Theme configuration for icons used in the selection snackbar.
         */
        public var icons: AiutaSelectionSnackbarThemeIcons? = null

        /**
         * Color configuration for the selection snackbar elements.
         */
        public var colors: AiutaSelectionSnackbarThemeColors? = null

        /**
         * Creates an [AiutaSelectionSnackbarTheme] instance with the configured properties.
         *
         * @return Configured [AiutaSelectionSnackbarTheme] instance
         * @throws IllegalArgumentException if required properties are not set
         */
        public fun build(): AiutaSelectionSnackbarTheme {
            val parentClass = "AiutaSelectionSnackbarTheme"

            return AiutaSelectionSnackbarTheme(
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
 * Extension function for configuring selection snackbar theme within an [AiutaTheme.Builder].
 *
 * This extension provides a convenient DSL for configuring the selection snackbar theme
 * as part of the main theme setup.
 *
 * ```kotlin
 * theme {
 *     selectionSnackbar {
 *         strings = AiutaSelectionSnackbarThemeStrings.Default()
 *         icons = AiutaSelectionSnackbarThemeIcons.Default()
 *         colors = AiutaSelectionSnackbarThemeColors.Default()
 *     }
 *     // Configure other theme components...
 * }
 * ```
 *
 * @param block Configuration block for setting up the selection snackbar theme
 * @return The theme builder for method chaining
 * @see AiutaTheme.Builder
 * @see AiutaSelectionSnackbarTheme.Builder
 */
public inline fun AiutaTheme.Builder.selectionSnackbar(
    block: AiutaSelectionSnackbarTheme.Builder.() -> Unit,
): AiutaTheme.Builder = apply {
    selectionSnackbar = AiutaSelectionSnackbarTheme.Builder().apply(block).build()
}

package com.aiuta.fashionsdk.configuration.ui.theme.selection.colors

import androidx.compose.ui.graphics.Color

/**
 * Color configuration for selection snackbar.
 *
 * This interface defines the colors used in selection-related snackbar
 * throughout the SDK. It provides consistent selection styling
 * that can be customized to match your app's design system.
 *
 *
 * @property selectionBackground Background color for the selection snackbar
 */
public interface AiutaSelectionSnackbarThemeColors {
    public val selectionBackground: Color

    /**
     * Default implementation of [AiutaSelectionSnackbarThemeColors].
     *
     * This class provides standard colors for selection snackbars:
     * - Selection background: Black (#000000)
     *
     * ```kotlin
     * theme {
     *     selectionSnackbar {
     *         colors = AiutaSelectionSnackbarThemeColors.Default()
     *     }
     * }
     * ```
     */
    public class Default : AiutaSelectionSnackbarThemeColors {
        override val selectionBackground: Color = Color(0xFF000000)
    }
}

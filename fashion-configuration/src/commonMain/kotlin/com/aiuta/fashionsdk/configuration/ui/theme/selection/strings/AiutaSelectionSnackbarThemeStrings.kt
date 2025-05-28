package com.aiuta.fashionsdk.configuration.ui.theme.selection.strings

/**
 * String configuration for selection snackbar.
 *
 * This interface defines the text strings used in selection-related snackbar
 * throughout the SDK. It provides consistent selection text
 * that can be customized to match your app's localization requirements.
 *
 *
 * @property select Text for the select action button
 * @property cancel Text for the cancel action button
 * @property selectAll Text for selecting all items
 * @property unselectAll Text for unselecting all items
 */
public interface AiutaSelectionSnackbarThemeStrings {
    public val select: String
    public val cancel: String
    public val selectAll: String
    public val unselectAll: String

    /**
     * Default implementation of [AiutaSelectionSnackbarThemeStrings].
     *
     * This class provides standard English text strings for selection snackbars.
     *
     * ```kotlin
     * theme {
     *     selectionSnackbar {
     *         strings = AiutaSelectionSnackbarThemeStrings.Default()
     *     }
     * }
     * ```
     */
    public class Default : AiutaSelectionSnackbarThemeStrings {
        override val select: String = "Select"
        override val cancel: String = "Cancel"
        override val selectAll: String = "Select All"
        override val unselectAll: String = "Unselect all"
    }
}

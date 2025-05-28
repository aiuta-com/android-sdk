package com.aiuta.fashionsdk.configuration.defaults.theme.selection

import com.aiuta.fashionsdk.configuration.defaults.icons.theme.selection.DefaultAiutaSelectionSnackbarThemeIcons
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.selection.colors.AiutaSelectionSnackbarThemeColors
import com.aiuta.fashionsdk.configuration.ui.theme.selection.selectionSnackbar
import com.aiuta.fashionsdk.configuration.ui.theme.selection.strings.AiutaSelectionSnackbarThemeStrings

/**
 * Configures the default selection snackbar theme for the Aiuta SDK.
 *
 * This function sets up the selection snackbar with default strings, icons, and colors.
 *
 * @return The updated [AiutaTheme.Builder] instance.
 */
public fun AiutaTheme.Builder.defaultSelectionSnackbar(): AiutaTheme.Builder = selectionSnackbar {
    strings = AiutaSelectionSnackbarThemeStrings.Default()
    icons = DefaultAiutaSelectionSnackbarThemeIcons()
    colors = AiutaSelectionSnackbarThemeColors.Default()
}

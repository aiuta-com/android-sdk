package com.aiuta.fashionsdk.tryon.compose.defaults.theme.selection

import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.selection.colors.AiutaSelectionSnackbarThemeColors
import com.aiuta.fashionsdk.configuration.ui.theme.selection.selectionSnackbar
import com.aiuta.fashionsdk.configuration.ui.theme.selection.strings.AiutaSelectionSnackbarThemeStrings
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.theme.selection.DefaultAiutaSelectionSnackbarThemeIcons

public fun AiutaTheme.Builder.defaultSelectionSnackbar() {
    selectionSnackbar {
        strings = AiutaSelectionSnackbarThemeStrings.Default()
        icons = DefaultAiutaSelectionSnackbarThemeIcons()
        colors = AiutaSelectionSnackbarThemeColors.Default()
    }
}

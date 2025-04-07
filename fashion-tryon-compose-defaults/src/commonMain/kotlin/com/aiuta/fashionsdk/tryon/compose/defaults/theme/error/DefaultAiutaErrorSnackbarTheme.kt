package com.aiuta.fashionsdk.tryon.compose.defaults.theme.error

import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.error.colors.AiutaErrorSnackbarThemeColors
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.error.errorSnackbar
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.error.strings.AiutaErrorSnackbarThemeStrings
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.theme.error.DefaultAiutaErrorSnackbarThemeIcons

public fun AiutaTheme.Builder.defaultErrorSnackbar() {
    errorSnackbar {
        strings = AiutaErrorSnackbarThemeStrings.Default()
        icons = DefaultAiutaErrorSnackbarThemeIcons()
        colors = AiutaErrorSnackbarThemeColors.Default()
    }
}

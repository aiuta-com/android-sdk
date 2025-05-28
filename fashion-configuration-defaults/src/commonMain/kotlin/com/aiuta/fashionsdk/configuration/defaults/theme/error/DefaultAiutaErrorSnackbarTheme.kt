package com.aiuta.fashionsdk.configuration.defaults.theme.error

import com.aiuta.fashionsdk.configuration.defaults.icons.theme.error.DefaultAiutaErrorSnackbarThemeIcons
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.error.colors.AiutaErrorSnackbarThemeColors
import com.aiuta.fashionsdk.configuration.ui.theme.error.errorSnackbar
import com.aiuta.fashionsdk.configuration.ui.theme.error.strings.AiutaErrorSnackbarThemeStrings

/**
 * Configures the default error snackbar theme for the Aiuta SDK.
 *
 * This function sets up the error snackbar with default strings, icons, and colors.
 *
 * @return The updated [AiutaTheme.Builder] instance.
 */
public fun AiutaTheme.Builder.defaultErrorSnackbar(): AiutaTheme.Builder = errorSnackbar {
    strings = AiutaErrorSnackbarThemeStrings.Default()
    icons = DefaultAiutaErrorSnackbarThemeIcons()
    colors = AiutaErrorSnackbarThemeColors.Default()
}

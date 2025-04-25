package com.aiuta.fashionsdk.configuration.internal.validation.theme

import com.aiuta.fashionsdk.configuration.debug.AiutaDebugSettings
import com.aiuta.fashionsdk.configuration.internal.validation.theme.error.validateWithSettings
import com.aiuta.fashionsdk.configuration.internal.validation.theme.selection.validateWithSettings
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.logger.AiutaLogger

internal fun AiutaTheme.validateWithSettings(
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
) {
    selectionSnackbar.validateWithSettings(logger, debugSettings)
    errorSnackbar.validateWithSettings(logger, debugSettings)
}

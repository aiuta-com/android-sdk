package com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.theme

import com.aiuta.fashionsdk.logger.AiutaLogger
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.theme.error.validateWithSettings
import com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.theme.selection.validateWithSettings
import com.aiuta.fashionsdk.tryon.compose.domain.models.debug.AiutaDebugSettings

internal fun AiutaTheme.validateWithSettings(
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
) {
    selectionSnackbar.validateWithSettings(logger, debugSettings)
    errorSnackbar.validateWithSettings(logger, debugSettings)
}

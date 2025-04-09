package com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.theme.error

import com.aiuta.fashionsdk.logger.AiutaLogger
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.error.AiutaErrorSnackbarTheme
import com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.validateStringWithSettings
import com.aiuta.fashionsdk.tryon.compose.domain.models.debug.AiutaDebugSettings

internal fun AiutaErrorSnackbarTheme.validateWithSettings(
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
) {
    strings.defaultErrorMessage.validateStringWithSettings(
        propertyName = "defaultErrorMessage",
        logger = logger,
        debugSettings = debugSettings,
    )
    strings.tryAgainButton.validateStringWithSettings(
        propertyName = "tryAgainButton",
        logger = logger,
        debugSettings = debugSettings,
    )
}

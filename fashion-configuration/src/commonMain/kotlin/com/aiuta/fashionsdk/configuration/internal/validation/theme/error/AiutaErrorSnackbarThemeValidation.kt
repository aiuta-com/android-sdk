package com.aiuta.fashionsdk.configuration.internal.validation.theme.error

import com.aiuta.fashionsdk.configuration.debug.AiutaDebugSettings
import com.aiuta.fashionsdk.configuration.internal.validation.validateStringWithSettings
import com.aiuta.fashionsdk.configuration.ui.theme.error.AiutaErrorSnackbarTheme
import com.aiuta.fashionsdk.logger.AiutaLogger

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

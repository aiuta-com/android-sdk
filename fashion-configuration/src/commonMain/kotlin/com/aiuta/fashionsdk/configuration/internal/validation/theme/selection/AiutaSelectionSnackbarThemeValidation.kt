package com.aiuta.fashionsdk.configuration.internal.validation.theme.selection

import com.aiuta.fashionsdk.configuration.debug.AiutaDebugSettings
import com.aiuta.fashionsdk.configuration.internal.validation.validateStringWithSettings
import com.aiuta.fashionsdk.configuration.ui.theme.selection.AiutaSelectionSnackbarTheme
import com.aiuta.fashionsdk.logger.AiutaLogger

internal fun AiutaSelectionSnackbarTheme.validateWithSettings(
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
) {
    strings.select.validateStringWithSettings(
        propertyName = "select",
        logger = logger,
        debugSettings = debugSettings,
    )
    strings.cancel.validateStringWithSettings(
        propertyName = "cancel",
        logger = logger,
        debugSettings = debugSettings,
    )
    strings.selectAll.validateStringWithSettings(
        propertyName = "selectAll",
        logger = logger,
        debugSettings = debugSettings,
    )
    strings.unselectAll.validateStringWithSettings(
        propertyName = "unselectAll",
        logger = logger,
        debugSettings = debugSettings,
    )
}

package com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.theme.selection

import com.aiuta.fashionsdk.logger.AiutaLogger
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.selection.AiutaSelectionSnackbarTheme
import com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.validateStringWithSettings
import com.aiuta.fashionsdk.tryon.compose.domain.models.debug.AiutaDebugSettings

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

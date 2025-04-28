package com.aiuta.fashionsdk.configuration.internal.validation.theme.powerby

import com.aiuta.fashionsdk.configuration.debug.AiutaDebugSettings
import com.aiuta.fashionsdk.configuration.internal.validation.validateStringWithSettings
import com.aiuta.fashionsdk.configuration.ui.theme.powerby.AiutaPowerBarTheme
import com.aiuta.fashionsdk.logger.AiutaLogger

internal fun AiutaPowerBarTheme.validateWithSettings(
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
) {
    // Strings
    strings.poweredByAiuta.validateStringWithSettings(
        propertyName = "poweredByAiuta",
        logger = logger,
        debugSettings = debugSettings,
    )
}

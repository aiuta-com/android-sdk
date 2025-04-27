package com.aiuta.fashionsdk.configuration.internal.validation.features.poweredby

import com.aiuta.fashionsdk.configuration.debug.AiutaDebugSettings
import com.aiuta.fashionsdk.configuration.features.powerby.AiutaPoweredByFeature
import com.aiuta.fashionsdk.configuration.internal.validation.validateStringWithSettings
import com.aiuta.fashionsdk.logger.AiutaLogger

internal fun AiutaPoweredByFeature.validateWithSettings(
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

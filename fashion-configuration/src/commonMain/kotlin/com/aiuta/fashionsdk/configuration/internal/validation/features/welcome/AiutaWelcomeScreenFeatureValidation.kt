package com.aiuta.fashionsdk.configuration.internal.validation.features.welcome

import com.aiuta.fashionsdk.configuration.debug.AiutaDebugSettings
import com.aiuta.fashionsdk.configuration.features.features.welcome.AiutaWelcomeScreenFeature
import com.aiuta.fashionsdk.configuration.internal.validation.validateStringWithSettings
import com.aiuta.fashionsdk.logger.AiutaLogger

internal fun AiutaWelcomeScreenFeature.validateWithSettings(
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
) {
    // Strings
    strings.welcomeTitle.validateStringWithSettings(
        propertyName = "welcomeTitle",
        logger = logger,
        debugSettings = debugSettings,
    )
    strings.welcomeDescription.validateStringWithSettings(
        propertyName = "welcomeDescription",
        logger = logger,
        debugSettings = debugSettings,
    )
    strings.welcomeButtonStart.validateStringWithSettings(
        propertyName = "welcomeButtonStart",
        logger = logger,
        debugSettings = debugSettings,
    )
}

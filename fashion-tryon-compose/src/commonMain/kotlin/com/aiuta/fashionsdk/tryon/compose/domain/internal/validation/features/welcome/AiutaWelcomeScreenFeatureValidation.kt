package com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.features.welcome

import com.aiuta.fashionsdk.logger.AiutaLogger
import com.aiuta.fashionsdk.tryon.compose.configuration.features.welcome.AiutaWelcomeScreenFeature
import com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.validateStringWithSettings
import com.aiuta.fashionsdk.tryon.compose.domain.models.debug.AiutaDebugSettings

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

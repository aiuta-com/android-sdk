package com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.welcome

import com.aiuta.fashionsdk.logger.AiutaLogger
import com.aiuta.fashionsdk.tryon.compose.configuration.debug.AiutaDebugSettings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.welcome.AiutaWelcomeScreenFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.welcome.strings.validationList
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.validateStringsWithSettings

internal fun AiutaWelcomeScreenFeature.validateWithSettings(
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
) {
    // Strings
    strings.validationList.validateStringsWithSettings(
        logger = logger,
        debugSettings = debugSettings,
    )
}

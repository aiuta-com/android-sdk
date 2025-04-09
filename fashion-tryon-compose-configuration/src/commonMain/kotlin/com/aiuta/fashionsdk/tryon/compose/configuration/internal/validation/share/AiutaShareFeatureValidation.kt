package com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.share

import com.aiuta.fashionsdk.logger.AiutaLogger
import com.aiuta.fashionsdk.tryon.compose.configuration.debug.AiutaDebugSettings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.share.AiutaShareFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.validateStringWithSettings

internal fun AiutaShareFeature.validateWithSettings(
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
) {
    // Strings
    strings.shareButton.validateStringWithSettings(
        propertyName = "shareButton",
        logger = logger,
        debugSettings = debugSettings,
    )
}

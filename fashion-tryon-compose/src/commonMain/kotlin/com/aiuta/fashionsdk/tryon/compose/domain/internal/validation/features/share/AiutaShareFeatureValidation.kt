package com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.features.share

import com.aiuta.fashionsdk.logger.AiutaLogger
import com.aiuta.fashionsdk.tryon.compose.configuration.features.share.AiutaShareFeature
import com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.validateStringWithSettings
import com.aiuta.fashionsdk.tryon.compose.domain.models.debug.AiutaDebugSettings

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

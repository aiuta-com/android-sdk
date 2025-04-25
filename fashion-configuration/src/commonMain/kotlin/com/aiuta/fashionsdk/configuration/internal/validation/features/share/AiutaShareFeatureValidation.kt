package com.aiuta.fashionsdk.configuration.internal.validation.features.share

import com.aiuta.fashionsdk.configuration.debug.AiutaDebugSettings
import com.aiuta.fashionsdk.configuration.features.features.share.AiutaShareFeature
import com.aiuta.fashionsdk.configuration.internal.validation.validateStringWithSettings
import com.aiuta.fashionsdk.logger.AiutaLogger

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

package com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.features

import com.aiuta.fashionsdk.logger.AiutaLogger
import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeatures
import com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.features.consent.validateWithSettings
import com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.features.onboarding.validateWithSettings
import com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.features.selector.validateWithSettings
import com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.features.share.validateWithSettings
import com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.features.tryon.validateWithSettings
import com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.features.welcome.validateWithSettings
import com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.features.wishlist.validateWithSettings
import com.aiuta.fashionsdk.tryon.compose.domain.models.debug.AiutaDebugSettings

internal fun AiutaTryOnConfigurationFeatures.validateWithSettings(
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
) {
    welcomeScreen?.validateWithSettings(logger, debugSettings)
    onboarding?.validateWithSettings(logger, debugSettings)
    consent?.validateWithSettings(logger, debugSettings)
    imageSelector.validateWithSettings(logger, debugSettings)
    tryOn.validateWithSettings(logger, debugSettings)
    share?.validateWithSettings(logger, debugSettings)
    wishlist?.validateWithSettings(logger, debugSettings)
}

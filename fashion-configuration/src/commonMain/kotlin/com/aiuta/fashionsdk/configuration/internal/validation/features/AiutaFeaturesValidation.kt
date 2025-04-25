package com.aiuta.fashionsdk.configuration.internal.validation.features

import com.aiuta.fashionsdk.configuration.debug.AiutaDebugSettings
import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.internal.validation.features.consent.validateWithSettings
import com.aiuta.fashionsdk.configuration.internal.validation.features.onboarding.validateWithSettings
import com.aiuta.fashionsdk.configuration.internal.validation.features.selector.validateWithSettings
import com.aiuta.fashionsdk.configuration.internal.validation.features.share.validateWithSettings
import com.aiuta.fashionsdk.configuration.internal.validation.features.tryon.validateWithSettings
import com.aiuta.fashionsdk.configuration.internal.validation.features.welcome.validateWithSettings
import com.aiuta.fashionsdk.configuration.internal.validation.features.wishlist.validateWithSettings
import com.aiuta.fashionsdk.logger.AiutaLogger

internal fun AiutaFeatures.validateWithSettings(
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
) {
    welcomeScreen?.validateWithSettings(logger, debugSettings)
    onboarding?.validateWithSettings(logger, debugSettings)
    consent?.validateWithSettings(logger, debugSettings)
    imagePicker.validateWithSettings(logger, debugSettings)
    tryOn.validateWithSettings(logger, debugSettings)
    share?.validateWithSettings(logger, debugSettings)
    wishlist?.validateWithSettings(logger, debugSettings)
}

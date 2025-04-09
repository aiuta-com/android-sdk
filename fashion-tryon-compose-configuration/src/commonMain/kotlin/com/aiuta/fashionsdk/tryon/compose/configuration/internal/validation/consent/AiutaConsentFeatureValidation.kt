package com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.consent

import com.aiuta.fashionsdk.logger.AiutaLogger
import com.aiuta.fashionsdk.tryon.compose.configuration.debug.AiutaDebugSettings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.AiutaConsentFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.builtin.AiutaConsentBuiltInWithOnboardingPageFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.AiutaConsentStandaloneOnboardingPageFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.strings.validationList
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.validateListWithSettings
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.validateStringWithSettings
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.validateStringsWithSettings

internal fun AiutaConsentFeature.validateWithSettings(
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
) {
    when (this) {
        is AiutaConsentBuiltInWithOnboardingPageFeature -> {
            // Strings
            strings.consentHtml.validateStringWithSettings("consentHtml", logger, debugSettings)
        }

        is AiutaConsentStandaloneOnboardingPageFeature -> {
            // Strings
            strings.validationList.validateStringsWithSettings(
                logger = logger,
                debugSettings = debugSettings,
            )

            // Lists
            data.consents.validateListWithSettings("consents", logger, debugSettings)
        }
    }
}

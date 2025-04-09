package com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.features.consent

import com.aiuta.fashionsdk.logger.AiutaLogger
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.AiutaConsentFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.builtin.AiutaConsentBuiltInWithOnboardingPageFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.AiutaConsentStandaloneOnboardingPageFeature
import com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.validateListWithSettings
import com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.validateStringWithSettings
import com.aiuta.fashionsdk.tryon.compose.domain.models.debug.AiutaDebugSettings

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
            strings.consentPageTitle.validateStringWithSettings(
                propertyName = "consentPageTitle",
                logger = logger,
                debugSettings = debugSettings,
            )
            strings.consentTitle.validateStringWithSettings(
                propertyName = "consentTitle",
                logger = logger,
                debugSettings = debugSettings,
            )
            strings.consentDescriptionHtml.validateStringWithSettings(
                propertyName = "consentDescriptionHtml",
                logger = logger,
                debugSettings = debugSettings,
            )
            strings.consentFooterHtml.validateStringWithSettings(
                propertyName = "consentFooterHtml",
                logger = logger,
                debugSettings = debugSettings,
            )

            // Lists
            data.consents.validateListWithSettings("consents", logger, debugSettings)
        }
    }
}

package com.aiuta.fashionsdk.configuration.internal.validation.features.consent

import com.aiuta.fashionsdk.configuration.debug.AiutaDebugSettings
import com.aiuta.fashionsdk.configuration.features.consent.AiutaConsentEmbeddedIntoOnboardingFeature
import com.aiuta.fashionsdk.configuration.features.consent.AiutaConsentFeature
import com.aiuta.fashionsdk.configuration.features.consent.AiutaConsentStandaloneFeature
import com.aiuta.fashionsdk.configuration.internal.validation.validateListWithSettings
import com.aiuta.fashionsdk.configuration.internal.validation.validateStringWithSettings
import com.aiuta.fashionsdk.logger.AiutaLogger

internal fun AiutaConsentFeature.validateWithSettings(
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
) {
    when (this) {
        is AiutaConsentEmbeddedIntoOnboardingFeature -> {
            // Strings
            strings.consentHtml.validateStringWithSettings("consentHtml", logger, debugSettings)
        }

        is AiutaConsentStandaloneFeature -> {
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

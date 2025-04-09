package com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.onboarding

import com.aiuta.fashionsdk.logger.AiutaLogger
import com.aiuta.fashionsdk.tryon.compose.configuration.debug.AiutaDebugSettings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.AiutaOnboardingFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.bestresult.strings.validationList
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.strings.validationList
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.tryon.strings.validationList
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.validateListWithSettings
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.validateStringsWithSettings

internal fun AiutaOnboardingFeature.validateWithSettings(
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
) {
    // Strings
    // Features
    tryOnPage.strings.validationList.validateStringsWithSettings(
        logger = logger,
        debugSettings = debugSettings,
    )

    bestResultsPage?.strings?.validationList.validateStringsWithSettings(
        logger = logger,
        debugSettings = debugSettings,
    )

    // General
    strings.validationList.validateStringsWithSettings(
        logger = logger,
        debugSettings = debugSettings,
    )

    // Lists
    tryOnPage.images.onboardingTryOnItems.validateListWithSettings(
        "onboardingTryOnItems",
        logger,
        debugSettings,
    )
}

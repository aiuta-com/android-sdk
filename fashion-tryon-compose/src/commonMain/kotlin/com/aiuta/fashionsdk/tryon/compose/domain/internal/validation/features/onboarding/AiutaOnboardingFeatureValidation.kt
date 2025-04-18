package com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.features.onboarding

import com.aiuta.fashionsdk.logger.AiutaLogger
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.AiutaOnboardingFeature
import com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.validateListWithSettings
import com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.validateStringWithSettings
import com.aiuta.fashionsdk.tryon.compose.domain.models.debug.AiutaDebugSettings

internal fun AiutaOnboardingFeature.validateWithSettings(
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
) {
    // Strings
    // Features
    tryOnPage.strings.onboardingTryOnPageTitle.validateStringWithSettings(
        propertyName = "onboardingTryOnPageTitle",
        logger = logger,
        debugSettings = debugSettings,
    )
    tryOnPage.strings.onboardingTryOnTitle.validateStringWithSettings(
        propertyName = "onboardingTryOnTitle",
        logger = logger,
        debugSettings = debugSettings,
    )
    tryOnPage.strings.onboardingTryOnDescription.validateStringWithSettings(
        propertyName = "onboardingTryOnDescription",
        logger = logger,
        debugSettings = debugSettings,
    )

    bestResultsPage?.strings?.onboardingBestResultsPageTitle.validateStringWithSettings(
        propertyName = "onboardingBestResultsPageTitle",
        logger = logger,
        debugSettings = debugSettings,
    )
    bestResultsPage?.strings?.onboardingBestResultsTitle.validateStringWithSettings(
        propertyName = "onboardingBestResultsTitle",
        logger = logger,
        debugSettings = debugSettings,
    )
    bestResultsPage?.strings?.onboardingBestResultsDescription.validateStringWithSettings(
        propertyName = "onboardingBestResultsDescription",
        logger = logger,
        debugSettings = debugSettings,
    )

    // General
    strings.onboardingButtonNext.validateStringWithSettings(
        propertyName = "onboardingButtonNext",
        logger = logger,
        debugSettings = debugSettings,
    )
    strings.onboardingButtonStart.validateStringWithSettings(
        propertyName = "onboardingButtonStart",
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

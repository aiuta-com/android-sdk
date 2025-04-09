package com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.tryon

import com.aiuta.fashionsdk.logger.AiutaLogger
import com.aiuta.fashionsdk.tryon.compose.configuration.debug.AiutaDebugSettings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.AiutaTryOnFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.disclaimer.strings.validationList
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.feedback.other.strings.validationList
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.feedback.strings.validationList
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.history.strings.validationList
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.loading.strings.validationList
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.strings.validationList
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.validateListWithSettings
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.validateStringsWithSettings

internal fun AiutaTryOnFeature.validateWithSettings(
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
) {
    // Strings
    // Features
    loadingPage.strings.validationList.validateStringsWithSettings(
        logger = logger,
        debugSettings = debugSettings,
    )

    fitDisclaimer?.strings?.validationList?.validateStringsWithSettings(
        logger = logger,
        debugSettings = debugSettings,
    )

    feedback?.strings?.validationList?.validateStringsWithSettings(
        logger = logger,
        debugSettings = debugSettings,
    )

    feedback?.otherFeedback?.strings?.validationList?.validateStringsWithSettings(
        logger = logger,
        debugSettings = debugSettings,
    )

    generationsHistory?.strings?.validationList?.validateStringsWithSettings(
        logger = logger,
        debugSettings = debugSettings,
    )

    // General
    strings.validationList.validateStringsWithSettings(
        logger = logger,
        debugSettings = debugSettings,
    )

    // Lists
    loadingPage.styles.loadingStatusBackgroundGradient?.validateListWithSettings(
        propertyName = "loadingStatusBackgroundGradient",
        logger = logger,
        debugSettings = debugSettings,
    )

    feedback?.strings?.tryOnFeedbackOptions.validateListWithSettings(
        propertyName = "tryOnFeedbackOptions",
        logger = logger,
        debugSettings = debugSettings,
    )

    styles.tryOnButtonGradient?.validateListWithSettings(
        propertyName = "tryOnButtonGradient",
        logger = logger,
        debugSettings = debugSettings,
    )
}

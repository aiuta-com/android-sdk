package com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation

import com.aiuta.fashionsdk.logger.AiutaLogger
import com.aiuta.fashionsdk.logger.w
import com.aiuta.fashionsdk.tryon.compose.configuration.debug.AiutaDebugSettings
import com.aiuta.fashionsdk.tryon.compose.configuration.debug.AiutaValidationPolicy
import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.consent.validateWithSettings
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.models.AiutaStringValidationContainer
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.onboarding.validateWithSettings
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.selector.validateWithSettings
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.share.validateWithSettings
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.tryon.validateWithSettings
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.welcome.validateWithSettings
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.wishlist.validateWithSettings

internal fun AiutaTryOnConfigurationFeatures.validateStringsWithSettings(
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
): AiutaTryOnConfigurationFeatures {
    welcomeScreen?.validateWithSettings(logger, debugSettings)
    onboarding?.validateWithSettings(logger, debugSettings)
    consent?.validateWithSettings(logger, debugSettings)
    imageSelector.validateWithSettings(logger, debugSettings)
    tryOn.validateWithSettings(logger, debugSettings)
    share?.validateWithSettings(logger, debugSettings)
    wishlist?.validateWithSettings(logger, debugSettings)

    return this
}

// Strings
internal fun String?.validateStringWithSettings(
    propertyName: String,
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
) {
    if (this == null) return

    if (isEmpty()) {
        onValidationFailed(
            message = "VALIDATION POLICY: string $propertyName is empty",
            logger = logger,
            debugSettings = debugSettings,
        )
    }
}

internal fun List<AiutaStringValidationContainer>?.validateStringsWithSettings(
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
) {
    if (this == null) return

    this.forEach { container ->
        container.string.validateStringWithSettings(
            propertyName = container.propertyName,
            logger = logger,
            debugSettings = debugSettings,
        )
    }
}

// Lists
internal fun <T> List<T>?.validateListWithSettings(
    propertyName: String,
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
) {
    if (this == null) return

    if (isEmpty()) {
        onValidationFailed(
            message = "VALIDATION POLICY: list $propertyName is empty",
            logger = logger,
            debugSettings = debugSettings,
        )
    }
}

// Map
internal fun <K, V> Map<K, V>?.validateMapWithSettings(
    propertyName: String,
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
) {
    if (this == null) return

    if (isEmpty()) {
        onValidationFailed(
            message = "VALIDATION POLICY: map $propertyName is empty",
            logger = logger,
            debugSettings = debugSettings,
        )
    }
}

internal fun onValidationFailed(
    message: String,
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
) {
    when (debugSettings.emptyStringsPolicy) {
        AiutaValidationPolicy.IGNORE -> Unit
        AiutaValidationPolicy.WARNING -> logger?.w(message)
        AiutaValidationPolicy.FATAL -> error(message)
    }
}

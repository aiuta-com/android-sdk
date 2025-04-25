package com.aiuta.fashionsdk.configuration.internal.validation

import com.aiuta.fashionsdk.configuration.debug.AiutaDebugSettings
import com.aiuta.fashionsdk.configuration.debug.AiutaValidationPolicy
import com.aiuta.fashionsdk.logger.AiutaLogger
import com.aiuta.fashionsdk.logger.w

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

package com.aiuta.fashionsdk.configuration.debug

/**
 * Validation policy enum for controlling SDK validation behavior.
 *
 * This enum defines how the SDK should handle validation issues during
 * configuration and runtime. Different policies provide different levels
 * of strictness for development and production environments.
 *
 * ```kotlin
 * val strictSettings = AiutaDebugSettings(
 *     emptyStringsPolicy = AiutaValidationPolicy.FATAL,
 *     listSizePolicy = AiutaValidationPolicy.WARNING
 * )
 *
 * val lenientSettings = AiutaDebugSettings(
 *     emptyStringsPolicy = AiutaValidationPolicy.IGNORE,
 *     listSizePolicy = AiutaValidationPolicy.IGNORE
 * )
 * ```
 *
 * @see AiutaDebugSettings
 */
public enum class AiutaValidationPolicy {
    /**
     * Ignore validation issues completely.
     *
     * No logging or exceptions will be thrown for validation failures.
     * Use this in production environments where you want maximum performance
     * and are confident in your configuration.
     */
    IGNORE,

    /**
     * Log validation issues as warnings.
     *
     * Validation failures will be logged but won't crash the application.
     * This is the recommended setting for development and testing.
     */
    WARNING,

    /**
     * Treat validation issues as fatal errors.
     *
     * Validation failures will throw exceptions and crash the application.
     * Use this during development to catch configuration issues early.
     */
    FATAL,
}

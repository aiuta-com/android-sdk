package com.aiuta.fashionsdk.logger

import com.aiuta.fashionsdk.logger.AiutaLogger.Level
/**
 * [AiutaLogger] is an interface that defines the contract for logging functionality across the application.
 * It provides methods for logging messages at different priority levels and includes convenience extension
 * functions for each log level.
 *
 * The logger supports:
 * - Different priority levels (VERBOSE, DEBUG, INFO, WARN, ERROR)
 * - Optional tags to identify the source of the log
 * - Optional throwable objects for error logging
 * - Custom messages
 *
 * @see Level for available logging priority levels
 */

public interface AiutaLogger {
    /**
     * Write [message] and/or [throwable] to a logging destination.
     */
    public fun log(
        priority: Level,
        tag: String? = null,
        throwable: Throwable? = null,
        message: String,
    )

    /**
     * The priority level for a log message.
     */
    public enum class Level {
        VERBOSE,
        DEBUG,
        INFO,
        WARN,
        ERROR,
    }
}
/**
 * Logs a message at VERBOSE level.
 *
 * @param message The message to log
 * @param throwable Optional throwable to log
 * @param tag Optional tag to identify the source of the log
 */
public fun AiutaLogger.v(
    message: String,
    throwable: Throwable? = null,
    tag: String? = null,
) {
    log(Level.VERBOSE, tag, throwable, message)
}

/**
 * Logs a message at DEBUG level.
 *
 * @param message The message to log
 * @param throwable Optional throwable to log
 * @param tag Optional tag to identify the source of the log
 */
public fun AiutaLogger.d(
    message: String,
    throwable: Throwable? = null,
    tag: String? = null,
) {
    log(Level.DEBUG, tag, throwable, message)
}

/**
 * Logs a message at INFO level.
 *
 * @param message The message to log
 * @param throwable Optional throwable to log
 * @param tag Optional tag to identify the source of the log
 */
public fun AiutaLogger.i(
    message: String,
    throwable: Throwable? = null,
    tag: String? = null,
) {
    log(Level.INFO, tag, throwable, message)
}

/**
 * Logs a message at WARN level.
 *
 * @param message The message to log
 * @param throwable Optional throwable to log
 * @param tag Optional tag to identify the source of the log
 */
public fun AiutaLogger.w(
    message: String,
    throwable: Throwable? = null,
    tag: String? = null,
) {
    log(Level.WARN, tag, throwable, message)
}

/**
 * Logs a message at ERROR level.
 *
 * @param message The message to log
 * @param throwable Optional throwable to log
 * @param tag Optional tag to identify the source of the log
 */
public fun AiutaLogger.e(
    message: String,
    throwable: Throwable? = null,
    tag: String? = null,
) {
    log(Level.ERROR, tag, throwable, message)
}

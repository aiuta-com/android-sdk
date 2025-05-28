package com.aiuta.fashionsdk.logger

/**
 * Platform-specific implementation of native logging functionality.
 * This function is expected to be implemented differently for each platform.
 *
 * @param priority The priority level of the log message
 * @param tag Optional tag to identify the source of the log
 * @param throwable Optional throwable to log
 * @param message The message to log
 */
internal expect fun nativeLog(
    priority: AiutaLogger.Level,
    tag: String?,
    throwable: Throwable?,
    message: String,
)

/**
 * Debug implementation of [AiutaLogger] that uses platform-specific native logging.
 * This logger is primarily used for development and debugging purposes.
 */
public class DebugAiutaLogger : AiutaLogger {
    override fun log(
        priority: AiutaLogger.Level,
        tag: String?,
        throwable: Throwable?,
        message: String,
    ) {
        nativeLog(priority, tag, throwable, message)
    }
}

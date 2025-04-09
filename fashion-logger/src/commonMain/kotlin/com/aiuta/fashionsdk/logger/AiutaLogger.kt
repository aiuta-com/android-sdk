package com.aiuta.fashionsdk.logger

import com.aiuta.fashionsdk.logger.AiutaLogger.Level

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

public fun AiutaLogger.v(
    message: String,
    throwable: Throwable? = null,
    tag: String? = null,
) {
    log(Level.VERBOSE, tag, throwable, message)
}

public fun AiutaLogger.d(
    message: String,
    throwable: Throwable? = null,
    tag: String? = null,
) {
    log(Level.DEBUG, tag, throwable, message)
}

public fun AiutaLogger.i(
    message: String,
    throwable: Throwable? = null,
    tag: String? = null,
) {
    log(Level.INFO, tag, throwable, message)
}

public fun AiutaLogger.w(
    message: String,
    throwable: Throwable? = null,
    tag: String? = null,
) {
    log(Level.WARN, tag, throwable, message)
}

public fun AiutaLogger.e(
    message: String,
    throwable: Throwable? = null,
    tag: String? = null,
) {
    log(Level.ERROR, tag, throwable, message)
}

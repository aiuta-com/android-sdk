package com.aiuta.fashionsdk.logger

import android.util.Log

internal actual fun nativeLog(
    priority: AiutaLogger.Level,
    tag: String?,
    throwable: Throwable?,
    message: String,
) {
    Log.println(priority.toInt(), tag, message)
}

private fun AiutaLogger.Level.toInt() = when (this) {
    AiutaLogger.Level.VERBOSE -> Log.VERBOSE
    AiutaLogger.Level.DEBUG -> Log.DEBUG
    AiutaLogger.Level.INFO -> Log.INFO
    AiutaLogger.Level.WARN -> Log.WARN
    AiutaLogger.Level.ERROR -> Log.ERROR
}

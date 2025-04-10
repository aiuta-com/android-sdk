package com.aiuta.fashionsdk.logger

internal actual fun nativeLog(
    priority: AiutaLogger.Level,
    tag: String?,
    throwable: Throwable?,
    message: String,
) {
    println(message)
}

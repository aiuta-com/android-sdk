package com.aiuta.fashionsdk.logger

internal expect fun nativeLog(
    priority: AiutaLogger.Level,
    tag: String?,
    throwable: Throwable?,
    message: String,
)

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

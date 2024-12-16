package com.aiuta.fashionsdk.tryon.core.domain.utils

import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.AiutaTryOnExceptionType
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.AiutaTryOnGenerationException

internal suspend fun <T> tryOnExceptionArea(
    type: AiutaTryOnExceptionType,
    action: suspend () -> T,
): T {
    try {
        return action()
    } catch (e: AiutaTryOnGenerationException) {
        // Rethrow with original
        throw e
    } catch (e: Exception) {
        // Rethrow with custom try on exception
        throw AiutaTryOnGenerationException(type)
    }
}

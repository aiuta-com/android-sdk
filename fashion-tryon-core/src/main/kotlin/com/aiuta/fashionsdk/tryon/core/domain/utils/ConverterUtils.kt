package com.aiuta.fashionsdk.tryon.core.domain.utils

import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.TryOnExceptionType
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.TryOnGenerationException

internal suspend fun <T> tryOnExceptionArea(
    type: TryOnExceptionType,
    action: suspend () -> T,
): T {
    try {
        return action()
    } catch (e: TryOnGenerationException) {
        // Rethrow with original
        throw e
    } catch (e: Exception) {
        // Rethrow with custom try on exception
        throw TryOnGenerationException(type)
    }
}

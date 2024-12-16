package com.aiuta.fashionsdk.tryon.core.domain.utils

import com.aiuta.fashionsdk.tryon.core.domain.AiutaTryOnImpl
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.TryOnExceptionType
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.TryOnGenerationException
import kotlinx.coroutines.delay

/**
 * Will execute [action] and additional [times] attempts
 *
 * Can throw exception, because in last attempt we will execute pure [action]
 */
internal suspend fun <T> AiutaTryOnImpl.retryAction(
    times: Int = 0,
    action: suspend () -> T,
): T {
    repeat(times.coerceAtLeast(0)) {
        try {
            return action()
        } catch (exception: Exception) {
            // Ignore exception, because we retry it
        }
        delay(retryPolicies.retryDelay)
    }
    return action()
}

/**
 * Will execute [action] and additional [times] attempts for all exception, except [failingTypes] exceptions
 */
internal suspend fun <T> AiutaTryOnImpl.retryActionWithSpecificTypes(
    times: Int = 0,
    failingTypes: Set<TryOnExceptionType>,
    action: suspend () -> T,
): T {
    repeat(times.coerceAtLeast(0)) {
        try {
            return action()
        } catch (exception: TryOnGenerationException) {
            // Rethrow if not required type
            if (exception.type in failingTypes) {
                throw exception
            }
        } catch (e: Exception) {
            // Ignore exception, because we retry it
        }
        delay(retryPolicies.retryDelay)
    }
    return action()
}

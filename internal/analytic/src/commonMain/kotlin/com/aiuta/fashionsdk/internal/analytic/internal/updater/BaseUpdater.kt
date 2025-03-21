package com.aiuta.fashionsdk.internal.analytic.internal.updater

import kotlin.coroutines.coroutineContext
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

internal abstract class BaseUpdater {

    /**
     * Return version of [retryAction], but can throw [CancellationException]
     */
    suspend fun retryAction(block: suspend () -> Unit) {
        while (coroutineContext.isActive) {
            // Try to update
            try {
                // Execute action
                block()
            } catch (e: CancellationException) {
                // Rethrow cancellation exception
                throw e
            } catch (e: Exception) {
                // Just ignore, let's try one more ti,e
            }

            // Wait for new attempt if retrying
            delay(UPDATE_DELAY)
        }
    }

    private companion object {
        const val UPDATE_DELAY = 3000L
    }
}

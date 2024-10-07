package com.aiuta.fashionsdk.network.internal.plugins

import kotlinx.atomicfu.atomic
import kotlinx.coroutines.CompletableDeferred

/**
 * Copy-paste from Ktor AuthTokenHolder
 */
internal class AuthTokenHolder<T>(
    private val loadTokens: suspend (params: String?) -> T?,
) {
    private val refreshTokensDeferred = atomic<CompletableDeferred<T?>?>(null)
    private val loadTokensDeferred = atomic<CompletableDeferred<T?>?>(null)

    internal suspend fun loadToken(params: String? = null): T? {
        var deferred: CompletableDeferred<T?>?
        lateinit var newDeferred: CompletableDeferred<T?>
        while (true) {
            deferred = loadTokensDeferred.value
            val newValue = deferred ?: CompletableDeferred()
            if (loadTokensDeferred.compareAndSet(deferred, newValue)) {
                newDeferred = newValue
                break
            }
        }

        // if there's already a pending loadTokens(), just wait for it to complete
        if (deferred != null) {
            return deferred.await()
        }

        try {
            val newTokens = loadTokens(params)

            // [loadTokensDeferred.value] could be null by now (if clearToken() was called while
            // suspended), which is why we are using [newDeferred] to complete the suspending callback.
            newDeferred.complete(newTokens)

            return newTokens
        } catch (cause: Throwable) {
            newDeferred.completeExceptionally(cause)
            loadTokensDeferred.compareAndSet(newDeferred, null)
            throw cause
        }
    }

    internal suspend fun setToken(block: suspend () -> T?): T? {
        var deferred: CompletableDeferred<T?>?
        lateinit var newDeferred: CompletableDeferred<T?>
        while (true) {
            deferred = refreshTokensDeferred.value
            val newValue = deferred ?: CompletableDeferred()
            if (refreshTokensDeferred.compareAndSet(deferred, newValue)) {
                newDeferred = newValue
                break
            }
        }

        try {
            val newToken =
                if (deferred == null) {
                    val newTokens = block()

                    // [refreshTokensDeferred.value] could be null by now (if clearToken() was called while
                    // suspended), which is why we are using [newDeferred] to complete the suspending callback.
                    newDeferred.complete(newTokens)
                    refreshTokensDeferred.value = null
                    newTokens
                } else {
                    deferred.await()
                }
            loadTokensDeferred.value = CompletableDeferred(newToken)
            return newToken
        } catch (cause: Throwable) {
            newDeferred.completeExceptionally(cause)
            refreshTokensDeferred.compareAndSet(newDeferred, null)
            throw cause
        }
    }
}

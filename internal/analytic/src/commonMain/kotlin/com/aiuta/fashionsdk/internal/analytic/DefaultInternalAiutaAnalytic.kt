package com.aiuta.fashionsdk.internal.analytic

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.internal.analytic.internal.InternalAiutaAnalyticImpl
import kotlin.concurrent.Volatile
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized

/**
 * Extension for default creation of [InternalAiutaAnalytic].
 *
 * Will cache instance of [InternalAiutaAnalytic]
 */
public val Aiuta.internalAiutaAnalytic: InternalAiutaAnalytic
    get() = InternalAiutaAnalyticFactory.create(this)

public object InternalAiutaAnalyticFactory : SynchronizedObject() {
    @Volatile
    private var instance: InternalAiutaAnalytic? = null
    private var cachedSubscriptionId: String? = null

    public fun create(aiuta: Aiuta): InternalAiutaAnalytic {
        validateCacheInstance(newSubscriptionId = aiuta.subscriptionId)

        return instance ?: synchronized(this) {
            instance ?: InternalAiutaAnalyticImpl.getInstance(aiuta).also {
                instance = it
            }
        }
    }

    public fun getInternalAiutaAnalytic(): InternalAiutaAnalytic? {
        return instance
    }

    private fun validateCacheInstance(newSubscriptionId: String) {
        // We should remove cache, if we have new instance of api key
        if (newSubscriptionId != cachedSubscriptionId) {
            instance = null
            cachedSubscriptionId = newSubscriptionId
        }
    }
}

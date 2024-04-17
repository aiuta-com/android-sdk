package com.aiuta.fashionsdk.internal.analytic

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.internal.analytic.internal.InternalAiutaAnalyticImpl

/**
 * Extension for default creation of [InternalAiutaAnalytic].
 *
 * Will cache instance of [InternalAiutaAnalytic]
 */
public val Aiuta.internalAiutaAnalytic: InternalAiutaAnalytic
    get() = InternalAiutaAnalyticImpl.create(this)

package com.aiuta.fashionsdk.analytic

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.analytic.events.AnalyticEvent
import com.aiuta.fashionsdk.analytic.internal.AiutaAnalyticImpl
import kotlinx.coroutines.flow.Flow

/**
 * Default extension for creating instance of [AiutaAnalytic]
 */
public val Aiuta.analytic: AiutaAnalytic
    get() = AiutaAnalyticImpl.create(aiuta = this)

/**
 * Public entry point for receiving analytic events of SDK
 */
public interface AiutaAnalytic {
    /**
     * Flow for listening all events from internals of SDK
     */
    public val analyticFlow: Flow<AnalyticEvent>
}

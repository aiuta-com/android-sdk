package com.aiuta.fashionsdk.internal.analytic

import com.aiuta.fashionsdk.internal.analytic.model.ExternalAnalyticEvent
import com.aiuta.fashionsdk.internal.analytic.model.InternalAnalyticEvent
import kotlinx.coroutines.flow.Flow

/**
 * Internal interface of of analytic service for internal use
 */
public interface InternalAiutaAnalytic {
    /**
     * Flow for listening all events from internals
     * of SDK for public usage
     */
    public val analyticFlow: Flow<ExternalAnalyticEvent>

    /**
     * Send new event without additional info
     **/
    public fun sendEvent(event: InternalAnalyticEvent)
}

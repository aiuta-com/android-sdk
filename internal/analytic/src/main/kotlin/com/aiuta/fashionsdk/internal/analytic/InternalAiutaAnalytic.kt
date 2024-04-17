package com.aiuta.fashionsdk.internal.analytic

import com.aiuta.fashionsdk.internal.analytic.model.InternalAnalyticEvent
import com.aiuta.fashionsdk.internal.analytic.model.SharedAnalyticEvent
import kotlinx.coroutines.flow.Flow

/**
 * Internal interface of of analytic service for internal use
 */
public interface InternalAiutaAnalytic {
    /**
     * Flow for listening all events from internals
     * of SDK for public usage
     */
    public val analyticFlow: Flow<SharedAnalyticEvent>

    /**
     * Send new event without additional info
     **/
    public fun sendEvent(event: InternalAnalyticEvent)

    /**
     * Send new event with additional info
     *
     * @param event key of this event
     * @param params additional info in key-value format
     **/
    public fun sendEvent(
        event: InternalAnalyticEvent,
        params: Map<String, String?>,
    )

    /**
     * Send new event with additional info
     *
     * @param event key of this event
     * @param fillMap builder of additional info in key-value format
     **/
    public fun sendEvent(
        event: InternalAnalyticEvent,
        fillMap: MutableMap<String, String?>.() -> Unit,
    )
}

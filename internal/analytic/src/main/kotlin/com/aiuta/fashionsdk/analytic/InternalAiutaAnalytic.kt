package com.aiuta.fashionsdk.analytic

import com.aiuta.fashionsdk.analytic.model.AnalyticEvent

// TODO Description
public interface InternalAiutaAnalytic {
    /**
     * Send new event without additional info
     **/
    public fun sendEvent(event: AnalyticEvent)

    /**
     * Send new event with additional info
     *
     * @param event key of this event
     * @param params additional info in key-value format
     **/
    public fun sendEvent(
        event: AnalyticEvent,
        params: Map<String, String?>,
    )

    /**
     * Send new event with additional info
     *
     * @param event key of this event
     * @param fillMap builder of additional info in key-value format
     **/
    public fun sendEvent(
        event: AnalyticEvent,
        fillMap: MutableMap<String, String?>.() -> Unit,
    )
}

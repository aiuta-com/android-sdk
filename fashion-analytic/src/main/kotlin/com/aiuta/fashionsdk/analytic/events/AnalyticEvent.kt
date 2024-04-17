package com.aiuta.fashionsdk.analytic.events

import com.aiuta.fashionsdk.internal.analytic.model.SharedAnalyticEvent

/**
 * This is event for public usage of external applications
 *
 * @param name This is name of public event
 * @param params Some possible params of event
 */
public class AnalyticEvent(
    public val name: String,
    public val params: Map<String, String?>,
)

internal fun SharedAnalyticEvent.toPublic(): AnalyticEvent {
    return AnalyticEvent(
        name = name,
        params = params,
    )
}

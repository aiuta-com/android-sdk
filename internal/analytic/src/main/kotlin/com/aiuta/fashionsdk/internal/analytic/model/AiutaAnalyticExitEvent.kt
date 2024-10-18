package com.aiuta.fashionsdk.internal.analytic.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(InternalAnalyticEvent.EventType.EXIT_EVENT)
public class AiutaAnalyticExitEvent(
    @SerialName("pageId")
    public val pageId: AiutaAnalyticPageId,
) : ExternalAnalyticEvent

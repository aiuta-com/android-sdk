package com.aiuta.fashionsdk.analytics.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(AiutaAnalyticsEvent.EventType.EXIT_EVENT)
public class AiutaAnalyticsExitEvent(
    @SerialName("pageId")
    public override val pageId: AiutaAnalyticsPageId?,
    @SerialName("productId")
    public override val productId: String?,
) : AiutaAnalyticsEvent

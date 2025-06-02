package com.aiuta.fashionsdk.analytics.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(AiutaAnalyticEvent.EventType.SESSION_EVENT)
public class AiutaSessionEvent(
    @SerialName("pageId")
    override val pageId: AiutaAnalyticPageId? = null,
    @SerialName("productId")
    override val productId: String?,
    @SerialName("flow")
    public val flow: FlowType,
) : AiutaAnalyticEvent {
    @Serializable
    public enum class FlowType {
        @SerialName("tryOn")
        TRY_ON,

        @SerialName("history")
        HISTORY,
    }
}

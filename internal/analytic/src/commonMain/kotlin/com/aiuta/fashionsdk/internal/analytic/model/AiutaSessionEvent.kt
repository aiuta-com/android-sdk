package com.aiuta.fashionsdk.internal.analytic.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(InternalAnalyticEvent.EventType.SESSION_EVENT)
public class AiutaSessionEvent(
    @SerialName("pageId")
    override val pageId: AiutaAnalyticPageId? = null,
    @SerialName("productId")
    override val productId: String?,
    @SerialName("flow")
    public val flow: FlowType,
) : InternalAnalyticEvent {
    @Serializable
    public enum class FlowType {
        @SerialName("tryOn")
        TRY_ON,

        @SerialName("history")
        HISTORY,
    }
}

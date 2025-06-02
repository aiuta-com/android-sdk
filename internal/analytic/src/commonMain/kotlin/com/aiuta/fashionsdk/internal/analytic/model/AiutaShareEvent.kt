package com.aiuta.fashionsdk.internal.analytic.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(InternalAnalyticEvent.EventType.SHARE_EVENT)
public class AiutaShareEvent(
    @SerialName("pageId")
    override val pageId: AiutaAnalyticPageId?,
    @SerialName("productId")
    override val productId: String?,
    @SerialName("event")
    public val event: AiutaShareEventType,
    @SerialName("targetId")
    public val targetId: String?,
) : ExternalAnalyticEvent

@Serializable
public enum class AiutaShareEventType {
    @SerialName("initiated")
    INITIATED,

    @SerialName("succeeded")
    SUCCEEDED,
}

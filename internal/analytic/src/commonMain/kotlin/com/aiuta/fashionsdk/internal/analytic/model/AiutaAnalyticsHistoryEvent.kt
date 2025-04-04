package com.aiuta.fashionsdk.internal.analytic.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(InternalAnalyticEvent.EventType.HISTORY_EVENT)
public class AiutaAnalyticsHistoryEvent(
    @SerialName("event")
    public val event: AiutaAnalyticsHistoryEventType,
    @SerialName("pageId")
    public override val pageId: AiutaAnalyticPageId?,
    @SerialName("productId")
    public override val productId: String?,
) : ExternalAnalyticEvent

@Serializable
public enum class AiutaAnalyticsHistoryEventType {
    @SerialName("generatedImageShared")
    GENERATED_IMAGE_SHARED,

    @SerialName("generatedImageDeleted")
    GENERATED_IMAGE_DELETED,
}

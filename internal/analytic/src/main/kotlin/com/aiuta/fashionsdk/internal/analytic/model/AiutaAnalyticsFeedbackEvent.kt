package com.aiuta.fashionsdk.internal.analytic.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(InternalAnalyticEvent.EventType.FEEDBACK_EVENT)
public class AiutaAnalyticsFeedbackEvent(
    @SerialName("event")
    public val event: AiutaAnalyticsFeedbackEventType,
    @SerialName("negativeFeedbackOptionIndex")
    public val negativeFeedbackOptionIndex: Int? = null,
    @SerialName("negativeFeedbackText")
    public val negativeFeedbackText: String? = null,
    @SerialName("pageId")
    public override val pageId: AiutaAnalyticPageId?,
    @SerialName("productId")
    public override val productId: String?,
) : ExternalAnalyticEvent

@Serializable
public enum class AiutaAnalyticsFeedbackEventType {
    @SerialName("positive")
    POSITIVE,

    @SerialName("negative")
    NEGATIVE,
}

package com.aiuta.fashionsdk.internal.analytic.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(InternalAnalyticEvent.EventType.PAGE_EVENT)
public class AiutaAnalyticPageEvent(
    @SerialName("pageId")
    public override val pageId: AiutaAnalyticPageId,
    @SerialName("productId")
    public override val productId: String,
) : ExternalAnalyticEvent

@Serializable
public enum class AiutaAnalyticPageId {
    @SerialName("welcome")
    WELCOME,

    @SerialName("howItWorks")
    HOW_IT_WORKS,

    @SerialName("bestResults")
    BEST_RESULTS,

    @SerialName("consent")
    CONSENT,

    @SerialName("imagePicker")
    IMAGE_PICKER,

    @SerialName("loading")
    LOADING,

    @SerialName("results")
    RESULTS,

    @SerialName("history")
    HISTORY,
}

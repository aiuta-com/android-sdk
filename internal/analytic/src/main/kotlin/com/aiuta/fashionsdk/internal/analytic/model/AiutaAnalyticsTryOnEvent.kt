package com.aiuta.fashionsdk.internal.analytic.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(InternalAnalyticEvent.EventType.TRY_ON_EVENT)
public class AiutaAnalyticsTryOnEvent(
    @SerialName("event")
    public val event: AiutaAnalyticsTryOnEventType,
    @SerialName("errorMessage")
    public val errorMessage: String? = null,
    @SerialName("pageId")
    public override val pageId: AiutaAnalyticPageId?,
    @SerialName("productId")
    public override val productId: String?,
) : ExternalAnalyticEvent

@Serializable
public enum class AiutaAnalyticsTryOnEventType {
    @SerialName("photoUploaded")
    PHOTO_UPLOADED,

    @SerialName("tryOnStarted")
    TRY_ON_STARTED,

    @SerialName("tryOnFinished")
    TRY_ON_FINISHED,

    @SerialName("tryOnAborted")
    TRY_ON_ABORTED,

    @SerialName("tryOnError")
    TRY_ON_ERROR,
}

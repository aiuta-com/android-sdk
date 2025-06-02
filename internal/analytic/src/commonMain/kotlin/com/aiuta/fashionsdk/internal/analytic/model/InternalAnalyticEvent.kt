package com.aiuta.fashionsdk.internal.analytic.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("type")
public sealed interface InternalAnalyticEvent {
    public val pageId: AiutaAnalyticPageId?

    public val productId: String?

    public object EventType {
        public const val CONFIGURE_EVENT: String = "configure"
        public const val SESSION_EVENT: String = "session"
        public const val PAGE_EVENT: String = "page"
        public const val ONBOARDING_EVENT: String = "onboarding"
        public const val PICKER_EVENT: String = "picker"
        public const val TRY_ON_EVENT: String = "tryOn"
        public const val RESULTS_EVENT: String = "results"
        public const val FEEDBACK_EVENT: String = "feedback"
        public const val HISTORY_EVENT: String = "history"
        public const val SHARE_EVENT: String = "share"
        public const val EXIT_EVENT: String = "exit"

        // Internal
        public const val TERMINATE_EVENT: String = "terminateEvent"
        public const val START_TRYON_EVENT: String = "startTryOnProcessEvent"
    }
}

@Serializable
@SerialName(InternalAnalyticEvent.EventType.TERMINATE_EVENT)
public class TerminateEvent(
    @SerialName("pageId")
    override val pageId: AiutaAnalyticPageId? = null,
    @SerialName("productId")
    override val productId: String?,
) : InternalAnalyticEvent

@Serializable
@SerialName(InternalAnalyticEvent.EventType.START_TRYON_EVENT)
public class StartTryOnEvent(
    @SerialName("pageId")
    override val pageId: AiutaAnalyticPageId? = null,
    @SerialName("productId")
    override val productId: String?,
    @SerialName("origin")
    public val origin: TryOnOrigin,
) : InternalAnalyticEvent {
    @Serializable
    public enum class TryOnOrigin {
        @SerialName("selectedPhoto")
        SELECTED_PHOTO,

        @SerialName("tryOnButton")
        TRY_ON_BUTTON,

        @SerialName("retakeButton")
        RETAKE_BUTTON,

        @SerialName("retryNotification")
        RETRY_NOTIFICATION,
    }
}

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
        public const val PAGE_EVENT: String = "pageEvent"
        public const val ONBOARDING_EVENT: String = "onboardingEvent"
        public const val PICKER_EVENT: String = "pickerEvent"
        public const val EXIT_EVENT: String = "exitEvent"
        public const val TRY_ON_EVENT: String = "tryOnEvent"
        public const val RESULTS_EVENT: String = "resultsEvent"
        public const val FEEDBACK_EVENT: String = "feedbackEvent"
        public const val HISTORY_EVENT: String = "historyEvent"

        // Internal
        public const val CONFIGURE_EVENT: String = "configureEvent"
        public const val SESSION_EVENT: String = "sessionEvent"
        public const val START_EVENT: String = "startEvent"
        public const val SHARE_EVENT: String = "shareEvent"
    }
}

@Serializable
@SerialName(InternalAnalyticEvent.EventType.CONFIGURE_EVENT)
public class ConfigureEvent(
    @SerialName("pageId")
    override val pageId: AiutaAnalyticPageId? = null,
    @SerialName("productId")
    override val productId: String? = null,
    @SerialName("mode")
    public val mode: SDKMode,
    @SerialName("authentication")
    public val authenticationType: AuthenticationType,
    // General toggles
    @SerialName("isHistoryAvailable")
    public val isHistoryEnable: Boolean,
    @SerialName("isWishlistAvailable")
    public val isWishlistAvailable: Boolean,
    @SerialName("isPreOnboardingAvailable")
    public val isPreOnboardingAvailable: Boolean,
    @SerialName("isShareAvailable")
    public val isShareAvailable: Boolean,
    @SerialName("isHostDataProviderEnabled")
    public val isHostDataProviderEnabled: Boolean,
) : InternalAnalyticEvent {
    @Serializable
    public enum class SDKMode {
        @SerialName("fullScreen")
        FULL_SCREEN,

        @SerialName("bottomSheet")
        BOTTOM_SHEET,

        @SerialName("pageSheet")
        PAGE_SHEET,
    }

    @Serializable
    public enum class AuthenticationType {
        @SerialName("apiKey")
        API_KEY,

        @SerialName("jwt")
        JWT,
    }
}

@Serializable
@SerialName(InternalAnalyticEvent.EventType.SESSION_EVENT)
public class SessionEvent(
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

@Serializable
@SerialName(InternalAnalyticEvent.EventType.START_EVENT)
public class StartEvent(
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

@Serializable
@SerialName(InternalAnalyticEvent.EventType.SHARE_EVENT)
public class ShareEvent(
    @SerialName("pageId")
    override val pageId: AiutaAnalyticPageId?,
    @SerialName("productId")
    override val productId: String?,
    @SerialName("result")
    public val result: ShareResult = ShareResult.SUCCEEDED,
    @SerialName("target")
    public val target: String?,
) : InternalAnalyticEvent {
    @Serializable
    public enum class ShareResult {
        @SerialName("succeeded")
        SUCCEEDED,

        @SerialName("canceled")
        CANCELED,

        @SerialName("failed")
        FAILED,
    }
}

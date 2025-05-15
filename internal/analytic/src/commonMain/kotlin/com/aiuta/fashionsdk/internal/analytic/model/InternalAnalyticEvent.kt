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
        public const val ERROR_EVENT: String = "errorEvent"
        public const val TERMINATE_EVENT: String = "terminateEvent"
        public const val SESSION_EVENT: String = "sessionEvent"
        public const val START_TRYON_EVENT: String = "startTryOnProcessEvent"
        public const val SUCCESS_EVENT: String = "successEvent"
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
    @SerialName("authentication")
    public val authenticationType: AuthenticationType,
    @SerialName("сonsentType")
    public val сonsentType: ConsentType? = null,
    // General toggles
    @SerialName("welcomeScreenFeatureEnabled")
    public val welcomeScreenFeatureEnabled: Boolean,
    @SerialName("onboardingFeatureEnabled")
    public val onboardingFeatureEnabled: Boolean,
    @SerialName("onboardingBestResultsPageFeatureEnabled")
    public val onboardingBestResultsPageFeatureEnabled: Boolean,
    @SerialName("imagePickerCameraFeatureEnabled")
    public val imagePickerCameraFeatureEnabled: Boolean,
    @SerialName("imagePickerPredefinedModelFeatureEnabled")
    public val imagePickerPredefinedModelFeatureEnabled: Boolean,
    @SerialName("imagePickerUploadsHistoryFeatureEnabled")
    public val imagePickerUploadsHistoryFeatureEnabled: Boolean,
    @SerialName("tryOnFitDisclaimerFeatureEnabled")
    public val tryOnFitDisclaimerFeatureEnabled: Boolean,
    @SerialName("tryOnFeedbackFeatureEnabled")
    public val tryOnFeedbackFeatureEnabled: Boolean,
    @SerialName("tryOnFeedbackOtherFeatureEnabled")
    public val tryOnFeedbackOtherFeatureEnabled: Boolean,
    @SerialName("tryOnGenerationsHistoryFeatureEnabled")
    public val tryOnGenerationsHistoryFeatureEnabled: Boolean,
    @SerialName("tryOnMultiItemFeatureEnabled")
    public val tryOnMultiItemFeatureEnabled: Boolean,
    @SerialName("tryOnWithOtherPhotoFeatureEnabled")
    public val tryOnWithOtherPhotoFeatureEnabled: Boolean,
    @SerialName("shareFeatureEnabled")
    public val shareFeatureEnabled: Boolean,
    @SerialName("shareWatermarkFeatureEnabled")
    public val shareWatermarkFeatureEnabled: Boolean,
    @SerialName("wishlistFeatureEnabled")
    public val wishlistFeatureEnabled: Boolean,
) : InternalAnalyticEvent {

    @Serializable
    public enum class AuthenticationType {
        @SerialName("apiKey")
        API_KEY,

        @SerialName("jwt")
        JWT,
    }

    @Serializable
    public enum class ConsentType {
        @SerialName("embeddedIntoOnboarding")
        EMBEDDED_INTO_ONBOARDING,

        @SerialName("standaloneOnboardingPage")
        STANDALONE_ONBOARDING_PAGE,

        @SerialName("standaloneImagePickerPage")
        STANDALONE_IMAGE_PICKER_PAGE,
    }
}

@Serializable
@SerialName(InternalAnalyticEvent.EventType.ERROR_EVENT)
public class ErrorEvent(
    @SerialName("pageId")
    override val pageId: AiutaAnalyticPageId? = null,
    @SerialName("productId")
    override val productId: String?,
    @SerialName("error")
    public val error: ErrorType,
) : InternalAnalyticEvent {
    @Serializable
    public enum class ErrorType {
        @SerialName("preparePhotoFailed")
        PREPARE_PHOTO_FAILED,

        @SerialName("uploadPhotoFailed")
        UPLOAD_PHOTO_FAILED,

        @SerialName("startOperationFailed")
        START_OPERATION_FAILED,

        @SerialName("operationFailed")
        OPERATION_FAILED,

        @SerialName("operationAborted")
        OPERATION_ABORTED_FAILED,

        @SerialName("operationTimeout")
        OPERATION_TIMEOUT_FAILED,

        @SerialName("operationEmptyResults")
        OPERATION_EMPTY_RESULTS_FAILED,

        @SerialName("downloadResultFailed")
        DOWNLOAD_RESULT_FAILED,
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

@Serializable
@SerialName(InternalAnalyticEvent.EventType.SUCCESS_EVENT)
public class SuccessEvent(
    @SerialName("pageId")
    override val pageId: AiutaAnalyticPageId? = null,
    @SerialName("productId")
    override val productId: String?,
    @SerialName("uploadDuration")
    public val uploadDuration: Double,
    @SerialName("tryOnDuration")
    public val tryOnDuration: Double,
    @SerialName("downloadDuration")
    public val downloadDuration: Double,
    @SerialName("totalDuration")
    public val totalDuration: Double,
) : InternalAnalyticEvent

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

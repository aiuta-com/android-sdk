package com.aiuta.fashionsdk.internal.analytic.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("type")
public sealed interface InternalAnalyticEvent {
    public val pageId: AiutaAnalyticPageId

    public val productId: String

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
        public const val CONFIGURE_EVENT: String = "Configure"
        public const val START_SESSION_EVENT: String = "StartSession"
        public const val FINISH_SESSION_EVENT: String = "FinishSession"
        public const val START_ONBOARDING_EVENT: String = "StartOnBoarding"
        public const val CONTINUE_ONBOARDING_EVENT: String = "ContinueOnBoarding"
        public const val OPEN_MAIN_SCREEN_EVENT: String = "OpenMainScreen"
        public const val TAP_CHANGE_PHOTO_EVENT: String = "TapChangePhoto"
        public const val SELECT_OLD_PHOTOS_EVENT: String = "SelectOldPhotos"
        public const val START_TRY_ON_EVENT: String = "StartTryOn"
        public const val FINISH_TRY_ON_EVENT: String = "FinishTryOn"
        public const val TRY_ON_ERROR_EVENT: String = "TryOnError"
        public const val VIEW_GENERATED_IMAGE_EVENT: String = "ViewGeneratedImage"
        public const val UPDATE_RESULTS_SCREEN_EVENT: String = "UpdateResultsScreen"
        public const val VIEW_MORE_TO_TRY_ON_EVENT: String = "ViewMoreToTryOn"
        public const val TAP_MORE_TO_TRY_ON_EVENT: String = "TapMoreToTryOn"
        public const val SELECT_MORE_TO_TRY_ON_EVENT: String = "SelectMoreToTryOn"
        public const val SHARE_GENERATED_IMAGE_EVENT: String = "ShareGeneratedImage"
        public const val GENERATION_FEEDBACK_EVENT: String = "GenerationFeedback"
        public const val LIKE_GENERATION_FEEDBACK_EVENT: String = "LikeGenerationFeedback"
        public const val DISLIKE_GENERATION_FEEDBACK_EVENT: String = "DislikeGenerationFeedback"
        public const val SHARE_SUCCESSFULLY_EVENT: String = "ShareSuccessfully"
    }
}

// Config
// TODO
@Serializable
@SerialName(InternalAnalyticEvent.EventType.CONFIGURE_EVENT)
public class Configure(
    @SerialName("pageId")
    override val pageId: AiutaAnalyticPageId,
    @SerialName("productId")
    override val productId: String,
    @SerialName("has_custom_configuration")
    public val hasCustomConfiguration: String,
    @SerialName("photo_selection_limit")
    public val photoSelectionLimit: String,
    @SerialName("is_watermark_provided")
    public val isWatermarkProvided: String,
    @SerialName("is_history_enable")
    public val isHistoryEnable: String,
    @SerialName("is_powered_by_visible")
    public val isPoweredByVisible: String,
) : InternalAnalyticEvent

// ResultsScreen
// TODO
@Serializable
@SerialName(InternalAnalyticEvent.EventType.SHARE_SUCCESSFULLY_EVENT)
public class ShareSuccessfully(
    @SerialName("pageId")
    override val pageId: AiutaAnalyticPageId,
    @SerialName("productId")
    override val productId: String,
    @SerialName("origin")
    public val origin: String? = null,
    @SerialName("count")
    public val count: String,
    @SerialName("target")
    public val target: String? = null,
    @SerialName("additional_share_info")
    public val additionalShareInfo: String? = null,
) : InternalAnalyticEvent

package com.aiuta.fashionsdk.internal.analytic.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("type")
public sealed interface InternalAnalyticEvent {
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
@Serializable
@SerialName(InternalAnalyticEvent.EventType.CONFIGURE_EVENT)
public class Configure(
    @SerialName("name")
    public val name: String = "Configure",
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

// Session
@Serializable
@SerialName(InternalAnalyticEvent.EventType.START_SESSION_EVENT)
public class StartSession(
    @SerialName("name")
    public val name: String = "StartSession",
    @SerialName("sku_id")
    public val skuId: String,
    @SerialName("sku_catalog_name")
    public val skuCatalogName: String? = null,
    @SerialName("related_sku_count")
    public val relatedSkuCount: String,
    @SerialName("price")
    public val price: String,
    @SerialName("price_discounted")
    public val priceDiscounted: String? = null,
    @SerialName("store")
    public val store: String,
    @SerialName("additional_share_info")
    public val additionalShareInfo: String? = null,
) : InternalAnalyticEvent

@Serializable
@SerialName(InternalAnalyticEvent.EventType.FINISH_SESSION_EVENT)
public class FinishSession(
    @SerialName("name")
    public val name: String = "FinishSession",
    @SerialName("action")
    public val action: String,
    @SerialName("origin")
    public val origin: String,
    @SerialName("sku_id")
    public val skuId: String,
    @SerialName("sku_catalog_name")
    public val skuCatalogName: String? = null,
) : InternalAnalyticEvent {
    public enum class Action(public val value: String) {
        NONE("None"),
        ADD_TO_CART("AddToCart"),
        ADD_TO_WISHLIST("AddToWishlist"),
        SHOW_SKU_INFO("ShowSkuInfo"),
    }

    public enum class Origin(public val value: String) {
        SKU_POPUP("SkuPopup"),
        RESULT_SCREEN("ResultsScreen"),
        MORE_TO_TRYON("MoreToTry"),
        MAIN_SCREEN("MainScreen"),

        // TODO Add to analytic
        ONBOARDING_SCREEN("OnboardingScreen"),
        PREONBOARDING_SCREEN("PreonboardingScreen"),
    }
}

// Onboarding
@Serializable
@SerialName(InternalAnalyticEvent.EventType.START_ONBOARDING_EVENT)
public class StartOnBoarding(
    @SerialName("name")
    public val name: String = "StartOnBoarding",
) : InternalAnalyticEvent

@Serializable
@SerialName(InternalAnalyticEvent.EventType.CONTINUE_ONBOARDING_EVENT)
public class ContinueOnBoarding(
    @SerialName("name")
    public val name: String = "ContinueOnBoarding",
    @SerialName("page")
    public val page: String,
) : InternalAnalyticEvent

// Main screen
@Serializable
@SerialName(InternalAnalyticEvent.EventType.OPEN_MAIN_SCREEN_EVENT)
public class OpenMainScreen(
    @SerialName("name")
    public val name: String = "OpenMainScreen",
    @SerialName("last_photos_selection")
    public val lastPhotoSelection: String,
) : InternalAnalyticEvent

@Serializable
@SerialName(InternalAnalyticEvent.EventType.TAP_CHANGE_PHOTO_EVENT)
public class TapChangePhoto(
    @SerialName("name")
    public val name: String = "TapChangePhoto",
    @SerialName("has_current_photos")
    public val hasCurrentPhotos: String,
    @SerialName("has_history_photos")
    public val hasHistoryPhotos: String,
) : InternalAnalyticEvent

@Serializable
@SerialName(InternalAnalyticEvent.EventType.SELECT_OLD_PHOTOS_EVENT)
public class SelectOldPhotos(
    @SerialName("name")
    public val name: String = "SelectOldPhotos",
    @SerialName("count")
    public val count: String,
) : InternalAnalyticEvent

// Try on
@Serializable
@SerialName(InternalAnalyticEvent.EventType.START_TRY_ON_EVENT)
public class StartTryOn(
    @SerialName("name")
    public val name: String = "StartTryOn",
    @SerialName("sku_id")
    public val skuId: String,
    @SerialName("sku_catalog_name")
    public val skuCatalogName: String? = null,
) : InternalAnalyticEvent

@Serializable
@SerialName(InternalAnalyticEvent.EventType.FINISH_TRY_ON_EVENT)
public class FinishTryOn(
    @SerialName("name")
    public val name: String = "FinishTryOn",
    @SerialName("sku_id")
    public val skuId: String,
    @SerialName("sku_catalog_name")
    public val skuCatalogName: String? = null,
    @SerialName("generation_time")
    public val generationTime: String,
) : InternalAnalyticEvent

@Serializable
@SerialName(InternalAnalyticEvent.EventType.TRY_ON_ERROR_EVENT)
public class TryOnError(
    @SerialName("name")
    public val name: String = "TryOnError",
    @SerialName("type")
    public val type: String,
) : InternalAnalyticEvent {
    public enum class Type(public val value: String) {
        UPLOAD_FAILED("UploadFailed"),
        TRY_ON_START_FAILED("TryOnStartFailed"),
        TRY_ON_OPERATION_FAILED("TryOnOperationFailed"),
    }
}

// ResultsScreen
@Serializable
@SerialName(InternalAnalyticEvent.EventType.VIEW_GENERATED_IMAGE_EVENT)
public class ViewGeneratedImage(
    @SerialName("name")
    public val name: String = "ViewGeneratedImage",
    @SerialName("image_number")
    public val imageNumber: String,
    @SerialName("navigation_type")
    public val navigationType: String,
    @SerialName("sku_id")
    public val skuId: String,
    @SerialName("sku_catalog_name")
    public val skuCatalogName: String? = null,
) : InternalAnalyticEvent {
    public enum class NavigationType(public val value: String) {
        THUMBNAIL("thumbnail"),
        SWIPE("swipe"),
    }
}

@Serializable
@SerialName(InternalAnalyticEvent.EventType.UPDATE_RESULTS_SCREEN_EVENT)
public class UpdateResultsScreen(
    @SerialName("name")
    public val name: String = "UpdateResultsScreen",
    @SerialName("sku_id")
    public val skuId: String,
    @SerialName("sku_catalog_name")
    public val skuCatalogName: String? = null,
    @SerialName("generated_photos")
    public val generatedPhotos: String,
    @SerialName("photos_in_progress")
    public val photosInProgress: String,
) : InternalAnalyticEvent

@Serializable
@SerialName(InternalAnalyticEvent.EventType.VIEW_MORE_TO_TRY_ON_EVENT)
public class ViewMoreToTryOn(
    @SerialName("name")
    public val name: String = "ViewMoreToTryOn",
) : InternalAnalyticEvent

@Serializable
@SerialName(InternalAnalyticEvent.EventType.TAP_MORE_TO_TRY_ON_EVENT)
public class TapMoreToTryOn(
    @SerialName("name")
    public val name: String = "TapMoreToTryOn",
    @SerialName("sku_id")
    public val skuId: String,
    @SerialName("sku_catalog_name")
    public val skuCatalogName: String? = null,
) : InternalAnalyticEvent

@Serializable
@SerialName(InternalAnalyticEvent.EventType.SELECT_MORE_TO_TRY_ON_EVENT)
public class SelectMoreToTryOn(
    @SerialName("name")
    public val name: String = "SelectMoreToTryOn",
    @SerialName("sku_id")
    public val skuId: String,
    @SerialName("sku_catalog_name")
    public val skuCatalogName: String? = null,
) : InternalAnalyticEvent

@Serializable
@SerialName(InternalAnalyticEvent.EventType.SHARE_GENERATED_IMAGE_EVENT)
public class ShareGeneratedImage(
    @SerialName("name")
    public val name: String = "GeneratedImageShared",
    @SerialName("origin")
    public val origin: String,
    @SerialName("count")
    public val count: String,
    @SerialName("additional_share_info")
    public val additionalShareInfo: String? = null,
) : InternalAnalyticEvent {
    public enum class Origin(public val value: String) {
        RESULT_SCREEN("ResultsScreen"),
        RESULT_FULLSCREEN("ResultsFullScreen"),
        HISTORY_SCREEN("HistoryScreeen"),
    }
}

@Serializable
@SerialName(InternalAnalyticEvent.EventType.GENERATION_FEEDBACK_EVENT)
public class GenerationFeedback(
    @SerialName("name")
    public val name: String = "GenerationFeedback",
    @SerialName("sku_id")
    public val skuId: String,
    @SerialName("sku_catalog_name")
    public val skuCatalogName: String? = null,
    @SerialName("generated_photo_position")
    public val generatedPhotoPosition: String,
    @SerialName("feedback")
    public val feedback: String? = null,
) : InternalAnalyticEvent

@Serializable
@SerialName(InternalAnalyticEvent.EventType.LIKE_GENERATION_FEEDBACK_EVENT)
public class LikeGenerationFeedback(
    @SerialName("name")
    public val name: String = "LikeGenerationFeedback",
    @SerialName("sku_id")
    public val skuId: String,
    @SerialName("sku_catalog_name")
    public val skuCatalogName: String? = null,
    @SerialName("generated_photo_position")
    public val generatedPhotoPosition: String,
) : InternalAnalyticEvent

@Serializable
@SerialName(InternalAnalyticEvent.EventType.DISLIKE_GENERATION_FEEDBACK_EVENT)
public class DislikeGenerationFeedback(
    @SerialName("name")
    public val name: String = "DislikeGenerationFeedback",
    @SerialName("sku_id")
    public val skuId: String,
    @SerialName("sku_catalog_name")
    public val skuCatalogName: String? = null,
    @SerialName("generated_photo_position")
    public val generatedPhotoPosition: String,
) : InternalAnalyticEvent

@Serializable
@SerialName(InternalAnalyticEvent.EventType.SHARE_SUCCESSFULLY_EVENT)
public class ShareSuccessfully(
    @SerialName("name")
    public val name: String = "ShareSuccessfully",
    @SerialName("origin")
    public val origin: String? = null,
    @SerialName("count")
    public val count: String,
    @SerialName("target")
    public val target: String? = null,
    @SerialName("additional_share_info")
    public val additionalShareInfo: String? = null,
) : InternalAnalyticEvent

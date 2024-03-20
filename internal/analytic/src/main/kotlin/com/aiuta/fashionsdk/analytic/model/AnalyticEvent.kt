package com.aiuta.fashionsdk.analytic.model

import kotlinx.serialization.Serializable

@Serializable
public sealed interface AnalyticEvent {
    public val name: String
}

// Config
public object Configure : AnalyticEvent {
    override val name: String = "Configure"

    public const val HAS_CUSTOM_CONFIGURATION_PARAM: String = "has_custom_configuration"
    public const val PHOTO_LIMIT_PARAM: String = "photo_selection_limit"
}

// Session
public object StartSession : AnalyticEvent {
    override val name: String = "StartSession"

    public const val SKU_ID_PARAM: String = "sku_id"
    public const val SKU_CATALOG_NAME_PARAM: String = "sku_catalog_name"
    public const val RELATED_SKU_COUNT_PARAM: String = "related_sku_count"
}

public object FinishSession : AnalyticEvent {
    override val name: String = "FinishSession"

    public const val ACTION_PARAM: String = "action"
    public const val ORIGIN_PARAM: String = "origin"
    public const val SKU_ID_PARAM: String = "sku_id"
    public const val SKU_CATALOG_NAME_PARAM: String = "sku_catalog_name"

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
    }
}

// Onboarding
public object StartOnBoarding : AnalyticEvent {
    override val name: String = "StartOnBoarding"
}

public object ContinueOnBoarding : AnalyticEvent {
    override val name: String = "ContinueOnBoarding"

    public const val PAGE_PARAM: String = "page"
}

public object FinishOnBoarding : AnalyticEvent {
    override val name: String = "FinishOnBoarding"
}

// Main screen
public object OpenMainScreen : AnalyticEvent {
    override val name: String = "OpenMainScreen"

    public const val LAST_PHOTO_SELECTION_PARAM: String = "last_photos_selection"
}

public object TapChangePhoto : AnalyticEvent {
    override val name: String = "TapChangePhoto"

    public const val HAS_CURRENT_PHOTOS_PARAM: String = "has_current_photos"
    public const val HAS_HISTORY_PHOTOS_PARAM: String = "has_history_photos"
}

// TODO Add this event with multi selector
public object SelectOldPhotos : AnalyticEvent {
    override val name: String = "SelectOldPhotos"

    public const val COUNT_PARAM: String = "count"
}

public object SelectNewPhotos : AnalyticEvent {
    override val name: String = "SelectNewPhotos"

    public const val FROM_CAMERA_PARAM: String = "from_camera"
    public const val FROM_GALLERY_PARAM: String = "from_gallery"
}

// Try on
public object StartTryOn : AnalyticEvent {
    override val name: String = "StartTryOn"

    public const val SKU_ID_PARAM: String = "sku_id"
    public const val SKU_CATALOG_NAME_PARAM: String = "sku_catalog_name"
    public const val PHOTOS_COUNT_PARAM: String = "photos_count"
}

public object StartUITryOn : AnalyticEvent {
    override val name: String = "StartUITryOn"

    public const val ORIGIN_PARAM: String = "origin"
    public const val SKU_ID_PARAM: String = "sku_id"
    public const val SKU_CATALOG_NAME_PARAM: String = "sku_catalog_name"
    public const val PHOTOS_COUNT_PARAM: String = "photos_count"

    public enum class Origin(public val value: String) {
        TRY_ON_BUTTON("TryOnButton"),
        SELECT_PHOTOS("ResultsScreen"),
    }
}

public object FinishTryOn : AnalyticEvent {
    override val name: String = "FinishTryOn"

    public const val SKU_ID_PARAM: String = "sku_id"
    public const val SKU_CATALOG_NAME_PARAM: String = "sku_catalog_name"
    public const val PHOTOS_COUNT_PARAM: String = "photos_count"
    public const val GENERATION_TIME_PARAM: String = "generation_time"
}

public object TryOnError : AnalyticEvent {
    override val name: String = "TryOnError"

    public const val TYPE_PARAM: String = "type"

    public enum class Type(public val value: String) {
        UPLOAD_FAILED("UploadFailed"),
        TRY_ON_START_FAILED("TryOnStartFailed"),
        TRY_ON_OPERATION_FAILED("TryOnOperationFailed"),
    }
}

// ResultsScreen
public object OpenResultsScreen : AnalyticEvent {
    override val name: String = "OpenResultsScreen"

    public const val SKU_ID_PARAM: String = "sku_id"
    public const val SKU_CATALOG_NAME_PARAM: String = "sku_catalog_name"
    public const val GENERATED_PHOTOS_PARAM: String = "generated_photos"
    public const val PHOTOS_IN_PROGRESS_PARAM: String = "photos_in_progress"
    public const val MORE_TO_TRY_ON_PARAM: String = "more_to_try_on"
}

public object ViewGeneratedImage : AnalyticEvent {
    override val name: String = "ViewGeneratedImage"

    public const val IMAGE_NUMBER_PARAM: String = "image_number"
    public const val NAVIGATION_TYPE_PARAM: String = "navigation_type"
    public const val SKU_ID_PARAM: String = "sku_id"
    public const val SKU_CATALOG_NAME_PARAM: String = "sku_catalog_name"

    public enum class NavigationType(public val value: String) {
        THUMBNAIL("thumbnail"),
        SWIPE("swipe"),
    }
}

// TODO Add with multi selector
public object UpdateResultsScreen : AnalyticEvent {
    override val name: String = "UpdateResultsScreen"

    public const val SKU_ID_PARAM: String = "sku_id"
    public const val SKU_CATALOG_NAME_PARAM: String = "sku_catalog_name"
    public const val GENERATED_PHOTOS_PARAM: String = "generated_photos"
    public const val PHOTOS_IN_PROGRESS_PARAM: String = "photos_in_progress"
}

public object ViewMoreToTryOn : AnalyticEvent {
    override val name: String = "ViewMoreToTryOn"
}

public object TapMoreToTryOn : AnalyticEvent {
    override val name: String = "TapMoreToTryOn"

    public const val SKU_ID_PARAM: String = "sku_id"
    public const val SKU_CATALOG_NAME_PARAM: String = "sku_catalog_name"
}

public object SelectMoreToTryOn : AnalyticEvent {
    override val name: String = "SelectMoreToTryOn"

    public const val SKU_ID_PARAM: String = "sku_id"
    public const val SKU_CATALOG_NAME_PARAM: String = "sku_catalog_name"
}

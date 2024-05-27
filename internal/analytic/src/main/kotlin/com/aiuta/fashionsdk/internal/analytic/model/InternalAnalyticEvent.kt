package com.aiuta.fashionsdk.internal.analytic.model

import kotlinx.serialization.Serializable

@Serializable
public sealed interface InternalAnalyticEvent {
    public val name: String
}

public sealed interface ShareableAnalyticEvent {
    public val publicName: String?

    public fun toShared(params: Map<String, String?> = emptyMap()): SharedAnalyticEvent
}

// Config
public object Configure : InternalAnalyticEvent {
    override val name: String = "Configure"

    public const val HAS_CUSTOM_CONFIGURATION_PARAM: String = "has_custom_configuration"
    public const val PHOTO_LIMIT_PARAM: String = "photo_selection_limit"
}

// Session
public object StartSession : InternalAnalyticEvent {
    override val name: String = "StartSession"

    public const val SKU_ID_PARAM: String = "sku_id"
    public const val SKU_CATALOG_NAME_PARAM: String = "sku_catalog_name"
    public const val RELATED_SKU_COUNT_PARAM: String = "related_sku_count"
    public const val DESCRIPTION_PARAM: String = "description"
    public const val PRICE_PARAM: String = "price"
    public const val PRICE_DISCOUNTED_PARAM: String = "price_discounted"
    public const val STORE_PARAM: String = "store"
    public const val ADDITIONAL_SHARE_INFO_PARAM: String = "additional_share_info"
}

public object FinishSession : InternalAnalyticEvent {
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
public object StartOnBoarding : InternalAnalyticEvent {
    override val name: String = "StartOnBoarding"
}

public object ContinueOnBoarding : InternalAnalyticEvent {
    override val name: String = "ContinueOnBoarding"

    public const val PAGE_PARAM: String = "page"
}

public object FinishOnBoarding : InternalAnalyticEvent, ShareableAnalyticEvent {
    override val name: String = "FinishOnBoarding"
    override val publicName: String = "OnBoardingCompleted"

    override fun toShared(params: Map<String, String?>): SharedAnalyticEvent {
        return SharedAnalyticEvent(name = publicName)
    }
}

// Main screen
public object OpenMainScreen : InternalAnalyticEvent {
    override val name: String = "OpenMainScreen"

    public const val LAST_PHOTO_SELECTION_PARAM: String = "last_photos_selection"
}

public object TapChangePhoto : InternalAnalyticEvent {
    override val name: String = "TapChangePhoto"

    public const val HAS_CURRENT_PHOTOS_PARAM: String = "has_current_photos"
    public const val HAS_HISTORY_PHOTOS_PARAM: String = "has_history_photos"
}

public object SelectOldPhotos : InternalAnalyticEvent {
    override val name: String = "SelectOldPhotos"

    public const val COUNT_PARAM: String = "count"
}

public object SelectNewPhotos : InternalAnalyticEvent, ShareableAnalyticEvent {
    override val name: String = "SelectNewPhotos"
    override val publicName: String? = null

    private const val PUBLIC_CAMERA_NAME = "NewPhotoTaken"
    private const val PUBLIC_GALLERY_NAME = "GalleryPhotosSelected"

    public const val FROM_CAMERA_PARAM: String = "from_camera"
    public const val FROM_GALLERY_PARAM: String = "from_gallery"

    override fun toShared(params: Map<String, String?>): SharedAnalyticEvent {
        val cameraCount = params[FROM_CAMERA_PARAM]?.toInt() ?: 0
        val galleryCount = params[FROM_GALLERY_PARAM]?.toInt() ?: 0

        return SharedAnalyticEvent(
            name =
                if (cameraCount != 0) {
                    PUBLIC_CAMERA_NAME
                } else {
                    PUBLIC_GALLERY_NAME
                },
            params =
                if (galleryCount != 0) {
                    mapOf("photos_count" to galleryCount.toString())
                } else {
                    emptyMap()
                },
        )
    }
}

// Try on
public object StartTryOn : InternalAnalyticEvent {
    override val name: String = "StartTryOn"

    public const val SKU_ID_PARAM: String = "sku_id"
    public const val SKU_CATALOG_NAME_PARAM: String = "sku_catalog_name"
}

public object StartUITryOn : InternalAnalyticEvent, ShareableAnalyticEvent {
    override val name: String = "StartUITryOn"
    override val publicName: String = "TryOnStarted"

    public const val ORIGIN_PARAM: String = "origin"
    public const val SKU_ID_PARAM: String = "sku_id"
    public const val SKU_CATALOG_NAME_PARAM: String = "sku_catalog_name"
    public const val PHOTOS_COUNT_PARAM: String = "photos_count"

    public enum class Origin(public val value: String) {
        TRY_ON_BUTTON("TryOnButton"),
        SELECT_PHOTOS("ResultsScreen"),
    }

    override fun toShared(params: Map<String, String?>): SharedAnalyticEvent {
        return SharedAnalyticEvent(
            name = publicName,
            params =
                mapOf(
                    SKU_ID_PARAM to params[SKU_ID_PARAM],
                    PHOTOS_COUNT_PARAM to params[PHOTOS_COUNT_PARAM],
                ),
        )
    }
}

public object FinishTryOn : InternalAnalyticEvent {
    override val name: String = "FinishTryOn"

    public const val SKU_ID_PARAM: String = "sku_id"
    public const val SKU_CATALOG_NAME_PARAM: String = "sku_catalog_name"
    public const val PHOTOS_COUNT_PARAM: String = "photos_count"
    public const val GENERATION_TIME_PARAM: String = "generation_time"
}

public object TryOnError : InternalAnalyticEvent {
    override val name: String = "TryOnError"

    public const val TYPE_PARAM: String = "type"

    public enum class Type(public val value: String) {
        UPLOAD_FAILED("UploadFailed"),
        TRY_ON_START_FAILED("TryOnStartFailed"),
        TRY_ON_OPERATION_FAILED("TryOnOperationFailed"),
    }
}

// ResultsScreen
public object OpenResultsScreen : InternalAnalyticEvent, ShareableAnalyticEvent {
    override val name: String = "OpenResultsScreen"
    override val publicName: String = "TryOnResultShown"

    public const val SKU_ID_PARAM: String = "sku_id"
    public const val SKU_CATALOG_NAME_PARAM: String = "sku_catalog_name"
    public const val GENERATED_PHOTOS_PARAM: String = "generated_photos"
    public const val PHOTOS_IN_PROGRESS_PARAM: String = "photos_in_progress"
    public const val MORE_TO_TRY_ON_PARAM: String = "more_to_try_on"

    override fun toShared(params: Map<String, String?>): SharedAnalyticEvent {
        return SharedAnalyticEvent(
            name = publicName,
            params =
                mapOf(
                    SKU_ID_PARAM to params[SKU_ID_PARAM],
                ),
        )
    }
}

public object ViewGeneratedImage : InternalAnalyticEvent {
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

public object UpdateResultsScreen : InternalAnalyticEvent {
    override val name: String = "UpdateResultsScreen"

    public const val SKU_ID_PARAM: String = "sku_id"
    public const val SKU_CATALOG_NAME_PARAM: String = "sku_catalog_name"
    public const val GENERATED_PHOTOS_PARAM: String = "generated_photos"
    public const val PHOTOS_IN_PROGRESS_PARAM: String = "photos_in_progress"
}

public object ViewMoreToTryOn : InternalAnalyticEvent {
    override val name: String = "ViewMoreToTryOn"
}

public object TapMoreToTryOn : InternalAnalyticEvent {
    override val name: String = "TapMoreToTryOn"

    public const val SKU_ID_PARAM: String = "sku_id"
    public const val SKU_CATALOG_NAME_PARAM: String = "sku_catalog_name"
}

public object SelectMoreToTryOn : InternalAnalyticEvent {
    override val name: String = "SelectMoreToTryOn"

    public const val SKU_ID_PARAM: String = "sku_id"
    public const val SKU_CATALOG_NAME_PARAM: String = "sku_catalog_name"
}

public object OpenHistoryScreen : InternalAnalyticEvent, ShareableAnalyticEvent {
    override val name: String = "OpenHistoryScreen"
    override val publicName: String = "HistoryScreenOpened"

    override fun toShared(params: Map<String, String?>): SharedAnalyticEvent {
        return SharedAnalyticEvent(name = publicName)
    }
}

public object ShareGeneratedImage : InternalAnalyticEvent, ShareableAnalyticEvent {
    override val name: String = "ShareGeneratedImage"
    override val publicName: String = "GeneratedImageShared"

    public const val ORIGIN_PARAM: String = "origin"
    public const val COUNT_PARAM: String = "count"

    public enum class Origin(public val value: String) {
        RESULT_SCREEN("ResultsScreen"),
        RESULT_FULLSCREEN("ResultsFullScreen"),
        HISTORY_SCREEN("HistoryScreeen"),
    }

    override fun toShared(params: Map<String, String?>): SharedAnalyticEvent {
        return SharedAnalyticEvent(name = publicName)
    }
}

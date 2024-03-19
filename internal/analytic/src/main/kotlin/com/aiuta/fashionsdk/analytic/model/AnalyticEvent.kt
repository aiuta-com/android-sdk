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

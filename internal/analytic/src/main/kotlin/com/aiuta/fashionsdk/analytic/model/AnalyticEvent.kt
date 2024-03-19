package com.aiuta.fashionsdk.analytic.model

import kotlinx.serialization.Serializable

@Serializable
public sealed interface AnalyticEvent {
    public val name: String
}

public object Configure : AnalyticEvent {
    override val name: String = "Configure"
}

public object StartSession : AnalyticEvent {
    override val name: String = "StartSession"

    public const val SKU_ID_PARAM: String = "sku_id"
    public const val SKU_CATALOG_NAME_PARAM: String = "sku_catalog_name"
    public const val RELATED_SKU_COUNT_PARAM: String = "related_sku_count"
}

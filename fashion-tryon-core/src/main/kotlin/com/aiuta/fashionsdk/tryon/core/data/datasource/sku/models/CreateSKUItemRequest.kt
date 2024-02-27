package com.aiuta.fashionsdk.tryon.core.data.datasource.sku.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CreateSKUItemRequest(
    @SerialName("category_google_id")
    val categoryGoogleId: String,
    @SerialName("category_google_name")
    val categoryGoogleName: String? = null,
    @SerialName("color")
    val color: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("images")
    val images: List<String>,
    @SerialName("sku_id")
    val skuId: String,
    @SerialName("title")
    val title: String,
)

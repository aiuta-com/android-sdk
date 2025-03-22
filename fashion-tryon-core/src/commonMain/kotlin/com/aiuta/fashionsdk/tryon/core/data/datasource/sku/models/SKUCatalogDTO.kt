package com.aiuta.fashionsdk.tryon.core.data.datasource.sku.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class SKUCatalogDTO(
    @SerialName("id")
    val id: String,
    @SerialName("sku_catalog_name")
    val catalogName: String,
)

package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class PoweredByStickerFeature(
    @SerialName("enabled")
    val enabled: Boolean,
    @SerialName("url_android")
    val urlAndroid: String? = null,
    @SerialName("url_ios")
    val urlIos: String? = null,
)

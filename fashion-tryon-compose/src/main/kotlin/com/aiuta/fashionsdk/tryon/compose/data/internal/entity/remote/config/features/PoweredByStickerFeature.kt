package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class PoweredByStickerFeature(
    @SerialName("url_android")
    val urlAndroid: String? = null,
    @SerialName("url_ios")
    val urlIos: String? = null,
)

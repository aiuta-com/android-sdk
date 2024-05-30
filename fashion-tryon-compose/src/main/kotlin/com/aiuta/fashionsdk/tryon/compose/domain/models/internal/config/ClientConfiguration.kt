package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config

import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features.PoweredByStickerFeature
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class ClientConfiguration(
    @SerialName("powered_by_sticker")
    val poweredByStickerFeature: PoweredByStickerFeature,
)

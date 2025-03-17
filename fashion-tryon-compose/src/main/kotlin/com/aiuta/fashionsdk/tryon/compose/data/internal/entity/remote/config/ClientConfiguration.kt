package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config

import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features.FeedbackFeature
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features.FitDisclaimerFeature
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features.PoweredByStickerFeature
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features.TryOnModelsCategory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class ClientConfiguration(
    @SerialName("powered_by_sticker")
    val poweredByStickerFeature: PoweredByStickerFeature? = null,
    @SerialName("feedback")
    val feedbackFeature: FeedbackFeature? = null,
    @SerialName("size_and_fit_disclaimer")
    val fitDisclaimerFeature: FitDisclaimerFeature? = null,
    @SerialName("predefined_try_on_models")
    val predefinedTryOnModels: List<TryOnModelsCategory>? = null,
)

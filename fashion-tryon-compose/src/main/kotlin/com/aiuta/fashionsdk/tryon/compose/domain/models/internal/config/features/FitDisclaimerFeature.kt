package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class FitDisclaimerFeature(
    @SerialName("title")
    val title: MultiLanguagePhrase,
    @SerialName("text")
    val text: MultiLanguagePhrase? = null,
)

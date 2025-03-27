package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class MultiLanguagePhrase(
    @SerialName("en")
    val englishTranslation: String? = null,
    @SerialName("ru")
    val russianTranslation: String? = null,
    @SerialName("tr")
    val turkishTranslation: String? = null,
)

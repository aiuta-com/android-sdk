package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class FeedbackFeature(
    @SerialName("title")
    val title: MultiLanguagePhrase? = null,
    @SerialName("main_options")
    val mainOptions: List<MultiLanguagePhrase>,
    @SerialName("plaintext_option")
    val plaintextOption: MultiLanguagePhrase? = null,
    @SerialName("plaintext_title")
    val plaintextTitle: MultiLanguagePhrase? = null,
    @SerialName("gratitude_message")
    val gratitudeMessage: MultiLanguagePhrase? = null,
)

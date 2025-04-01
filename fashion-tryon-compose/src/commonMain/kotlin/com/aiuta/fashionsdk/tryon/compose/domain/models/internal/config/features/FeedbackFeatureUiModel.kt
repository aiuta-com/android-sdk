package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features.FeedbackFeature

@Immutable
internal class FeedbackFeatureUiModel(
    val title: MultiLanguagePhraseUiModel? = null,
    val mainOptions: List<MultiLanguagePhraseUiModel>,
    val plaintextOption: MultiLanguagePhraseUiModel? = null,
    val plaintextTitle: MultiLanguagePhraseUiModel? = null,
    val gratitudeMessage: MultiLanguagePhraseUiModel? = null,
)

internal fun FeedbackFeature.toUiModel(): FeedbackFeatureUiModel = FeedbackFeatureUiModel(
    title = title?.toUiModel(),
    mainOptions = mainOptions.map { it.toUiModel() },
    plaintextOption = plaintextOption?.toUiModel(),
    plaintextTitle = plaintextTitle?.toUiModel(),
    gratitudeMessage = gratitudeMessage?.toUiModel(),
)

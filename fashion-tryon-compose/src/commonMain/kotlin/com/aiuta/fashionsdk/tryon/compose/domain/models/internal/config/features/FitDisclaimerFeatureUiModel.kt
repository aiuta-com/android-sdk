package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features.FitDisclaimerFeature

@Immutable
internal class FitDisclaimerFeatureUiModel(
    val title: MultiLanguagePhraseUiModel,
    val text: MultiLanguagePhraseUiModel? = null,
)

internal fun FitDisclaimerFeature.toUiModel(): FitDisclaimerFeatureUiModel = FitDisclaimerFeatureUiModel(
    title = title.toUiModel(),
    text = text?.toUiModel(),
)

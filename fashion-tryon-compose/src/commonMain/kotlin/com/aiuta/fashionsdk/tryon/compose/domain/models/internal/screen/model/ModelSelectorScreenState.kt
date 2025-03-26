package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.screen.model

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features.TryOnModelsCategoryUiModel

@Immutable
internal sealed interface ModelSelectorScreenState {
    object Loading : ModelSelectorScreenState

    object GeneralError : ModelSelectorScreenState

    object EmptyModelsListError : ModelSelectorScreenState

    class Content(val categories: List<TryOnModelsCategoryUiModel>) : ModelSelectorScreenState
}

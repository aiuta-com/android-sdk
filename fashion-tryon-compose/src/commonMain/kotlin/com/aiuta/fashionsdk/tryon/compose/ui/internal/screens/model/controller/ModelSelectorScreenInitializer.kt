package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.model.controller

import androidx.compose.runtime.MutableState
import com.aiuta.fashionsdk.configuration.features.picker.model.AiutaImagePickerPredefinedModelFeature
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.screen.model.ModelSelectorScreenState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.data.AiutaTryOnDataController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.data.provideTryOnModelsCategories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal fun CoroutineScope.initModelSelectorScreen(
    dataController: AiutaTryOnDataController,
    screenState: MutableState<ModelSelectorScreenState>,
    predefinedModelFeature: AiutaImagePickerPredefinedModelFeature,
    forceUpdate: Boolean = false,
) {
    launch {
        screenState.value = ModelSelectorScreenState.Loading

        dataController
            .provideTryOnModelsCategories(
                predefinedModelCategories = predefinedModelFeature.strings.predefinedModelCategories,
                forceUpdate = forceUpdate,
            )
            .onFailure {
                screenState.value = ModelSelectorScreenState.GeneralError
            }
            .onSuccess { result ->
                screenState.value =
                    when {
                        result == null -> ModelSelectorScreenState.GeneralError
                        result.isEmpty() -> ModelSelectorScreenState.EmptyModelsListError
                        else -> ModelSelectorScreenState.Content(
                            preferredCategoryId = predefinedModelFeature.data?.preferredCategoryId,
                            categories = result,
                        )
                    }
            }
    }
}

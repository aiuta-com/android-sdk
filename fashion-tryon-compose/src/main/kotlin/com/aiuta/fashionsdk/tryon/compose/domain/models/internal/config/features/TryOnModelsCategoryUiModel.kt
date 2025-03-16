package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features.TryOnModelsCategory
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage

@Immutable
internal class TryOnModelsCategoryUiModel(
    val category: String,
    val models: List<TryOnModelUiModel>,
) {
    @Immutable
    internal class TryOnModelUiModel(
        val id: String,
        val url: String,
    )
}

internal fun TryOnModelsCategory.toUiModel(
    stringResources: InternalAiutaTryOnLanguage,
): TryOnModelsCategoryUiModel {
    return TryOnModelsCategoryUiModel(
        category = category,
        models = models.mapNotNull { model -> model.toUiModel(stringResources) },
    )
}

internal fun TryOnModelsCategory.TryOnModel.toUiModel(
    stringResources: InternalAiutaTryOnLanguage,
): TryOnModelsCategoryUiModel.TryOnModelUiModel? {
    val translation =
        stringResources.modelSelectorCategories.find { translationWord ->
            translationWord.id == this.id
        }

    return translation?.let {
        TryOnModelsCategoryUiModel.TryOnModelUiModel(
            id = this.id,
            url = this.url,
        )
    }
}

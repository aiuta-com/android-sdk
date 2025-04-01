package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features.TryOnModelsCategory
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.UrlImage

@Immutable
internal data class TryOnModelsCategoryUiModel(
    val category: String,
    val models: List<TryOnModelUiModel>,
) {
    @Immutable
    internal data class TryOnModelUiModel(
        val id: String,
        val url: String,
    )
}

internal fun TryOnModelsCategory.toUiModel(
    stringResources: InternalAiutaTryOnLanguage,
): TryOnModelsCategoryUiModel? {
    val translation =
        stringResources.modelSelectorCategories.find { translationWord ->
            translationWord.id == this.category
        }

    return translation?.let {
        TryOnModelsCategoryUiModel(
            category = translation.translation,
            models = models.map { model -> model.toUiModel() },
        )
    }
}

internal fun TryOnModelsCategory.TryOnModel.toUiModel(): TryOnModelsCategoryUiModel.TryOnModelUiModel = TryOnModelsCategoryUiModel.TryOnModelUiModel(
    id = this.id,
    url = this.url,
)

internal fun TryOnModelsCategoryUiModel.TryOnModelUiModel.toUrlImage(): UrlImage = UrlImage(
    imageId = id,
    imageUrl = url,
)

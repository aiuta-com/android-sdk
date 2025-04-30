package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features.TryOnModelsCategory
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.UrlImage
import com.aiuta.fashionsdk.tryon.core.data.datasource.image.models.AiutaFileType

@Immutable
internal data class TryOnModelsCategoryUiModel(
    val category: String,
    val models: List<TryOnModelUiModel>,
) {
    @Immutable
    internal data class TryOnModelUiModel(
        val id: String,
        val url: String,
        val type: AiutaFileType,
    )
}

internal fun TryOnModelsCategory.toUiModel(
    predefinedModelCategories: Map<String, String>,
): TryOnModelsCategoryUiModel? = predefinedModelCategories[category]?.let { translation ->
    TryOnModelsCategoryUiModel(
        category = translation,
        models = models.map { model -> model.toUiModel() },
    )
}

internal fun TryOnModelsCategory.TryOnModel.toUiModel(): TryOnModelsCategoryUiModel.TryOnModelUiModel = TryOnModelsCategoryUiModel.TryOnModelUiModel(
    id = this.id,
    url = this.url,
    type = this.type,
)

internal fun TryOnModelsCategoryUiModel.TryOnModelUiModel.toUrlImage(): UrlImage = UrlImage(
    imageId = id,
    imageUrl = url,
    imageType = type,
)

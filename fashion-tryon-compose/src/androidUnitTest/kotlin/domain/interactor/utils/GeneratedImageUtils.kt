package domain.interactor.utils

import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImageUIModel

internal fun newImageList(range: IntRange): List<GeneratedImageUIModel> = range.map {
    GeneratedImageUIModel(
        id = "id-$it",
        imageUrl = "url-$it",
    )
}

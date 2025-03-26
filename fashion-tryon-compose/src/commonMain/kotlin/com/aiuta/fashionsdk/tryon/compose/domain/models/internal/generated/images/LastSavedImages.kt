package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperationUIModel
import com.aiuta.fashionsdk.tryon.core.domain.models.image.AiutaPlatformImage

@Immutable
internal sealed interface LastSavedImages {
    data class PlatformImageSource(
        val platformImages: List<AiutaPlatformImage>,
    ) : LastSavedImages

    sealed interface UrlSource : LastSavedImages {
        val urlImages: List<UrlImage>

        data class Base(
            override val urlImages: List<UrlImage>,
        ) : UrlSource

        data class PregeneratedModels(
            override val urlImages: List<UrlImage>,
        ) : UrlSource
    }

    object Empty : LastSavedImages
}

// Helpers
internal fun LastSavedImages.isNotEmpty(): Boolean {
    return imageSource.isNotEmpty()
}

internal val LastSavedImages.imageSource: List<LastSavedImageWrapper>
    get() {
        return when (this) {
            is LastSavedImages.PlatformImageSource ->
                platformImages.map {
                    LastSavedImageWrapper.SavedPlatformImage(it)
                }

            is LastSavedImages.UrlSource ->
                urlImages.map {
                    LastSavedImageWrapper.SavedUrlImage(
                        it,
                    )
                }

            is LastSavedImages.Empty -> emptyList()
        }
    }

internal val LastSavedImages.size: Int
    get() = imageSource.size

// Converters
internal fun GeneratedOperationUIModel.toLastSavedImages(): LastSavedImages {
    return LastSavedImages.UrlSource.Base(
        urlImages = urlImages,
    )
}

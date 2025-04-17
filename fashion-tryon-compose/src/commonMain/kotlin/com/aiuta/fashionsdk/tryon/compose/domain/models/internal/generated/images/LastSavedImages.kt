package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.io.AiutaPlatformFile
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperationUIModel

@Immutable
internal sealed interface LastSavedImages {
    data class PlatformImageSource(
        val platformFiles: List<AiutaPlatformFile>,
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
internal fun LastSavedImages.isNotEmpty(): Boolean = imageSource.isNotEmpty()

internal val LastSavedImages.imageSource: List<LastSavedImageWrapper>
    get() {
        return when (this) {
            is LastSavedImages.PlatformImageSource ->
                platformFiles.map {
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
internal fun GeneratedOperationUIModel.toLastSavedImages(): LastSavedImages = LastSavedImages.UrlSource.Base(
    urlImages = urlImages,
)

package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperationUIModel

@Immutable
internal sealed interface LastSavedImages {
    data class UriSource(
        val imageUris: List<String>,
    ) : LastSavedImages

    sealed interface UrlSource : LastSavedImages {
        val sourceImages: List<SourceImage>

        data class Base(
            override val sourceImages: List<SourceImage>,
        ) : UrlSource

        data class PregeneratedModels(
            override val sourceImages: List<SourceImage>,
        ) : UrlSource
    }

    object Empty : LastSavedImages
}

// Helpers
internal fun LastSavedImages.isEmpty(): Boolean {
    return imageSource.isEmpty()
}

internal fun LastSavedImages.isNotEmpty(): Boolean {
    return imageSource.isNotEmpty()
}

internal val LastSavedImages.imageSource: List<String>
    get() {
        return when (this) {
            is LastSavedImages.UriSource -> imageUris
            is LastSavedImages.UrlSource -> sourceImages.map { it.imageUrl }
            is LastSavedImages.Empty -> emptyList()
        }
    }

internal val LastSavedImages.size: Int
    get() = imageSource.size

// Converters
internal fun GeneratedOperationUIModel.toLastSavedImages(): LastSavedImages {
    return LastSavedImages.UrlSource.Base(
        sourceImages = sourceImages,
    )
}

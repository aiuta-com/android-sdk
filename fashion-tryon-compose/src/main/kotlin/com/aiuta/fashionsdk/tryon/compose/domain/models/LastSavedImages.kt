package com.aiuta.fashionsdk.tryon.compose.domain.models

import androidx.compose.runtime.Immutable

@Immutable
internal sealed interface LastSavedImages {
    class UriSource(
        val imageUris: List<String>,
    ) : LastSavedImages

    class UrlSource(
        val sourceImages: List<SourceImage>,
    ) : LastSavedImages

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
internal fun GeneratedOperation.toLastSavedImages(): LastSavedImages {
    return LastSavedImages.UrlSource(
        sourceImages = sourceImages,
    )
}

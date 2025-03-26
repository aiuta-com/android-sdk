package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.picker.gallery

import androidx.compose.runtime.Composable
import com.aiuta.fashionsdk.tryon.core.domain.models.image.AiutaPlatformImage

@Composable
internal expect fun rememberGalleryManager(onResult: (AiutaPlatformImage?) -> Unit): GalleryManager

internal expect class GalleryManager(
    onLaunch: () -> Unit,
) {
    fun launch()
}

package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.picker.camera

import androidx.compose.runtime.Composable
import com.aiuta.fashionsdk.tryon.core.domain.models.image.AiutaPlatformImage

@Composable
internal expect fun rememberCameraManager(onResult: (AiutaPlatformImage?) -> Unit): CameraManager

internal expect class CameraManager(
    onLaunch: () -> Unit,
) {
    fun launch()
}

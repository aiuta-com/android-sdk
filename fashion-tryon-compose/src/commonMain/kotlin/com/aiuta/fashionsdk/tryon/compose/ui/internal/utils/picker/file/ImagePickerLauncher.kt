package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.picker.file

import androidx.compose.runtime.Composable
import com.aiuta.fashionsdk.tryon.core.domain.models.file.AiutaPlatformFile

@Composable
internal expect fun rememberImagePickerLauncher(
    onResult: (List<AiutaPlatformFile>) -> Unit,
): ImagePickerLauncher

internal expect class ImagePickerLauncher(
    onLaunch: () -> Unit,
) {
    fun launch()
}

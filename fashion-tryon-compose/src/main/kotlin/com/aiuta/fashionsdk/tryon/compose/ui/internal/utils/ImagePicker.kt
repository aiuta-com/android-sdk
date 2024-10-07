package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration

// Camera Picker
@Composable
internal fun provideCameraPicker(
    success: (Boolean) -> Unit,
): ManagedActivityResultLauncher<Uri, *> {
    return rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture(),
    ) { hasImage ->
        success(hasImage)
    }
}

internal fun openCameraPicker(
    newImageUri: () -> Uri,
    getLauncher: () -> ManagedActivityResultLauncher<Uri, *>,
) {
    getLauncher().launch(newImageUri())
}

// Image Picker
@Composable
internal fun provideMultipleImagePicker(
    maxItems: Int = 10,
    success: (uris: List<Uri>) -> Unit,
): ManagedActivityResultLauncher<PickVisualMediaRequest, *> {
    return rememberLauncherForActivityResult(
        ActivityResultContracts.PickMultipleVisualMedia(maxItems = maxItems),
    ) { uris ->
        success(uris)
    }
}

@Composable
internal fun provideSingleImagePicker(
    success: (uris: Uri?) -> Unit,
): ManagedActivityResultLauncher<PickVisualMediaRequest, *> {
    return rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia(),
    ) { uris ->
        success(uris)
    }
}

internal fun openMultipleImagePicker(
    getLauncher: () -> ManagedActivityResultLauncher<PickVisualMediaRequest, *>,
) {
    getLauncher().launch(
        PickVisualMediaRequest(
            ActivityResultContracts.PickVisualMedia.ImageOnly,
        ),
    )
}

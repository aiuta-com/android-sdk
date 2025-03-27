package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.picker.camera

import android.content.ContentResolver
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.picker.newImageUri
import com.aiuta.fashionsdk.tryon.core.domain.models.image.AiutaPlatformImage

@Composable
internal actual fun rememberCameraManager(onResult: (AiutaPlatformImage?) -> Unit): CameraManager {
    val context = LocalContext.current
    val contentResolver: ContentResolver = context.contentResolver
    var tempPhotoUri by remember { mutableStateOf(value = Uri.EMPTY) }
    val cameraLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture(),
            onResult = { success ->
                if (success) {
                    val bitmap = BitmapUtils.getBitmapFromUri(tempPhotoUri, contentResolver)

                    bitmap?.let { onResult.invoke(AiutaPlatformImage(bitmap)) }
                }
            },
        )
    return remember {
        CameraManager(
            onLaunch = {
                tempPhotoUri = newImageUri(context)
                cameraLauncher.launch(tempPhotoUri)
            },
        )
    }
}

internal actual class CameraManager actual constructor(
    private val onLaunch: () -> Unit,
) {
    actual fun launch() {
        onLaunch()
    }
}

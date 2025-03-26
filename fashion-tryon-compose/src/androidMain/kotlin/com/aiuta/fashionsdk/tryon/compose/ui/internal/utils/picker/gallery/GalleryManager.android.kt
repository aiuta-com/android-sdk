package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.picker.gallery

import android.content.ContentResolver
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.picker.camera.BitmapUtils
import com.aiuta.fashionsdk.tryon.core.domain.models.image.AiutaPlatformImage

@Composable
internal actual fun rememberGalleryManager(
    onResult: (AiutaPlatformImage?) -> Unit,
): GalleryManager {
    val context = LocalContext.current
    val contentResolver: ContentResolver = context.contentResolver
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            val bitmap = uri?.let { BitmapUtils.getBitmapFromUri(it, contentResolver) }
            bitmap?.let { onResult.invoke(AiutaPlatformImage(bitmap)) }
        }
    return remember {
        GalleryManager(
            onLaunch = {
                galleryLauncher.launch(
                    PickVisualMediaRequest(
                        mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly,
                    ),
                )
            },
        )
    }
}

internal actual class GalleryManager actual constructor(private val onLaunch: () -> Unit) {
    actual fun launch() {
        onLaunch()
    }
}

package com.aiuta.fashionsdk.tryon.compose.uikit.internal.painter

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toComposeImageBitmap
import coil3.compose.rememberAsyncImagePainter
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.resources.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.resources.AiutaDrawableResource
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.resources.icon.AiutaIOSSkia

@Composable
internal actual fun painterResource(drawableResource: AiutaDrawableResource): Painter {
    return when (drawableResource) {
        // Multiplatform
        is AiutaComposeDrawableResource -> org.jetbrains.compose.resources.painterResource(drawableResource.resource)
        // IOS
        is AiutaIOSSkia -> {
            val imageBitmap = drawableResource.resource.toComposeImageBitmap()
            return rememberAsyncImagePainter(model = imageBitmap)
        }
        else -> rememberAsyncImagePainter(model = drawableResource.resource)
    }
}

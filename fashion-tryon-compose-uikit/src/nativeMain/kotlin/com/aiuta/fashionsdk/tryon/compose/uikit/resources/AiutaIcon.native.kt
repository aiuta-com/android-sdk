package com.aiuta.fashionsdk.tryon.compose.uikit.resources

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toComposeImageBitmap
import coil3.compose.rememberAsyncImagePainter
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.resources.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.resources.AiutaIcon
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.resources.icon.AiutaIOSSkia
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
@Composable
internal actual fun painterResource(icon: AiutaIcon): Painter {
    return when (val iconResource = icon.iconResource) {
        // Multiplatform
        is AiutaComposeDrawableResource -> org.jetbrains.compose.resources.painterResource(iconResource.resource)
        // IOS
        is AiutaIOSSkia -> {
            val imageBitmap = iconResource.resource.toComposeImageBitmap()
            return rememberAsyncImagePainter(model = imageBitmap)
        }
        else -> rememberAsyncImagePainter(model = icon.iconResource)
    }
}

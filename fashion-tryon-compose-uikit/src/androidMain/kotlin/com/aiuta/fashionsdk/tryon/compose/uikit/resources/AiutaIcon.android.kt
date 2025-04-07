package com.aiuta.fashionsdk.tryon.compose.uikit.resources

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import coil3.compose.rememberAsyncImagePainter
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.resources.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.resources.AiutaIcon
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaAndroidDrawable
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaAndroidDrawableRes

@Composable
internal actual fun painterResource(icon: AiutaIcon): Painter = when (val iconResource = icon.iconResource) {
    // Multiplatform
    is AiutaComposeDrawableResource -> org.jetbrains.compose.resources.painterResource(iconResource.resource)
    // Android
    is com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaAndroidDrawable -> rememberAsyncImagePainter(model = iconResource.resource)
    is com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaAndroidDrawableRes -> androidx.compose.ui.res.painterResource(iconResource.resource)
    // Fallback to coil
    else -> rememberAsyncImagePainter(model = icon.iconResource)
}

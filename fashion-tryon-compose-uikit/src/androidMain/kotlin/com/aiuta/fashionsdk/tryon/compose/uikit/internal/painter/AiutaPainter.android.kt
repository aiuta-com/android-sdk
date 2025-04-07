package com.aiuta.fashionsdk.tryon.compose.uikit.internal.painter

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import coil3.compose.rememberAsyncImagePainter
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.resources.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.resources.AiutaDrawableResource
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaAndroidDrawable
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaAndroidDrawableRes

@Composable
internal actual fun painterResource(drawableResource: AiutaDrawableResource): Painter = when (drawableResource) {
    // Multiplatform
    is AiutaComposeDrawableResource -> org.jetbrains.compose.resources.painterResource(drawableResource.resource)
    // Android
    is com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaAndroidDrawable -> rememberAsyncImagePainter(model = drawableResource.resource)
    is com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaAndroidDrawableRes -> androidx.compose.ui.res.painterResource(drawableResource.resource)
    // Fallback to coil
    else -> rememberAsyncImagePainter(model = drawableResource.resource)
}

package com.aiuta.fashionsdk.tryon.compose.uikit.resources.painter

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaDrawableResource

@Composable
public expect fun painterResource(drawableResource: AiutaDrawableResource): Painter

@Composable
internal fun commonPainterResource(drawableResource: AiutaDrawableResource): Painter = when (drawableResource) {
    is AiutaComposeDrawableResource -> org.jetbrains.compose.resources.painterResource(drawableResource.resource)
    else -> error("Not supported common drawable resource")
}

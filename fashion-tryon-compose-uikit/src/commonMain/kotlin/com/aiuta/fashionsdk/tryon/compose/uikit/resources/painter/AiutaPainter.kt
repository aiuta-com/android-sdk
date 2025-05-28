package com.aiuta.fashionsdk.tryon.compose.uikit.resources.painter

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaDrawableResource

/**
 * Creates a Painter from an AiutaDrawableResource.
 * This is a platform-specific implementation that loads drawable resources.
 *
 * @param drawableResource The drawable resource to create a painter from
 * @return A Painter that can be used to draw the resource
 */
@Composable
public expect fun painterResource(drawableResource: AiutaDrawableResource): Painter

@Composable
internal fun commonPainterResource(drawableResource: AiutaDrawableResource): Painter = when (drawableResource) {
    is AiutaComposeDrawableResource -> org.jetbrains.compose.resources.painterResource(drawableResource.resource)
    else -> error("Not supported common drawable resource")
}

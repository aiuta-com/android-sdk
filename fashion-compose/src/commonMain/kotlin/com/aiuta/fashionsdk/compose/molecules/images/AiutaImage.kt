package com.aiuta.fashionsdk.compose.molecules.images

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import coil3.compose.rememberAsyncImagePainter
import com.aiuta.fashionsdk.compose.tokens.images.AiutaImage

@Composable
public fun AiutaImage(
    image: AiutaImage,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
) {
    androidx.compose.foundation.Image(
        modifier = modifier,
        painter = rememberAsyncImagePainter(image.resource),
        contentScale = contentScale,
        contentDescription = contentDescription,
        alignment = alignment,
        alpha = alpha,
        colorFilter = colorFilter,
    )
}

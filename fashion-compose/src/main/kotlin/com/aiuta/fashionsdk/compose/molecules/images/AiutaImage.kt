package com.aiuta.fashionsdk.compose.molecules.images

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import com.aiuta.fashionsdk.compose.tokens.images.AiutaDrawableImage
import com.aiuta.fashionsdk.compose.tokens.images.AiutaImage
import com.aiuta.fashionsdk.compose.tokens.images.AiutaResourceImage

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
        painter =
            when (image) {
                is AiutaResourceImage -> painterResource(image.resource)
                is AiutaDrawableImage -> rememberAsyncImagePainter(image.resource)
            },
        contentScale = contentScale,
        contentDescription = contentDescription,
        alignment = alignment,
        alpha = alpha,
        colorFilter = colorFilter,
    )
}

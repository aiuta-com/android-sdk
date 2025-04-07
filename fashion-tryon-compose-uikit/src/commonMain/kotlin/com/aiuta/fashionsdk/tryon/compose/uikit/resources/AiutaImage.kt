package com.aiuta.fashionsdk.tryon.compose.uikit.resources

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import coil3.compose.AsyncImagePainter
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.resources.AiutaDrawableResource
import com.aiuta.fashionsdk.tryon.compose.uikit.internal.painter.painterResource
import com.aiuta.fashionsdk.tryon.compose.uikit.internal.progress.ErrorProgress
import com.aiuta.fashionsdk.tryon.compose.uikit.internal.utils.placeholderFadeConnecting

@Composable
public fun AiutaImage(
    image: AiutaDrawableResource,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
) {
    // No need to show loading, because it's iconResource image
    Image(
        modifier = modifier,
        painter = painterResource(drawableResource = image),
        contentScale = contentScale,
        contentDescription = contentDescription,
        alignment = alignment,
        alpha = alpha,
        colorFilter = colorFilter,
    )
}

@Composable
public fun AiutaImage(
    imageUrl: String?,
    contentDescription: String?,
    shapeDp: Dp,
    modifier: Modifier = Modifier,
    imageBuilder: ImageRequest.Builder = ImageRequest.Builder(LocalPlatformContext.current),
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
) {
    AiutaImage(
        imageUrl = imageUrl,
        contentDescription = contentDescription,
        shape = RoundedCornerShape(shapeDp),
        modifier = modifier,
        imageBuilder = imageBuilder,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter,
    )
}

@Composable
public fun AiutaImage(
    imageUrl: String?,
    contentDescription: String?,
    shape: CornerBasedShape,
    modifier: Modifier = Modifier,
    imageBuilder: ImageRequest.Builder = ImageRequest.Builder(LocalPlatformContext.current),
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
) {
    val imagePainter = rememberAsyncImagePainter(
        model = imageBuilder
            .data(imageUrl)
            .crossfade(true)
            .build(),
    )
    val imageModelState = imagePainter.state.collectAsState()
    val isShimmerVisible = remember {
        derivedStateOf { imageModelState.value is AsyncImagePainter.State.Loading }
    }

    when (imageModelState.value) {
        is AsyncImagePainter.State.Empty -> Unit
        is AsyncImagePainter.State.Error -> ErrorProgress(modifier = modifier)
        else -> {
            Image(
                modifier = modifier.placeholderFadeConnecting(
                    shape = shape,
                    visible = isShimmerVisible.value,
                ),
                painter = imagePainter,
                contentScale = contentScale,
                alignment = alignment,
                alpha = alpha,
                colorFilter = colorFilter,
                contentDescription = contentDescription,
            )
        }
    }
}

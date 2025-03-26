package com.aiuta.fashionsdk.tryon.compose.ui.internal.components.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.LastSavedImageWrapper
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress.ErrorProgress
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress.LoadingProgress

@Composable
internal fun ImagesContainer(
    modifier: Modifier = Modifier,
    getImages: () -> List<LastSavedImageWrapper>,
) {
    val coilContext = LocalPlatformContext.current
    val images = getImages()

    when (val image = images.firstOrNull()) {
        is LastSavedImageWrapper.SavedPlatformImage -> {
            Image(
                modifier = modifier,
                bitmap = image.image.imageBitmap,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }

        is LastSavedImageWrapper.SavedUrlImage -> {
            SubcomposeAsyncImage(
                modifier = modifier,
                model =
                    ImageRequest.Builder(coilContext)
                        .data(image.image.imageUrl)
                        .build(),
                contentDescription = null,
                loading = { LoadingProgress(modifier = Modifier.fillMaxSize()) },
                error = { ErrorProgress(modifier = Modifier.fillMaxSize()) },
                contentScale = ContentScale.Crop,
            )
        }

        null -> Unit
    }
}

package com.aiuta.fashionsdk.tryon.compose.ui.internal.components.images

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.aiuta.fashionsdk.compose.molecules.images.AiutaImage
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.LastSavedImageWrapper

@Composable
internal fun ImagesContainer(
    modifier: Modifier = Modifier,
    getImages: () -> List<LastSavedImageWrapper>,
) {
    val theme = LocalTheme.current

    val images = getImages()
    val sharedCornerShape = theme.shapes.mainImage

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
            AiutaImage(
                modifier = modifier,
                imageUrl = image.image.imageUrl,
                shape = sharedCornerShape,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }

        null -> Unit
    }
}

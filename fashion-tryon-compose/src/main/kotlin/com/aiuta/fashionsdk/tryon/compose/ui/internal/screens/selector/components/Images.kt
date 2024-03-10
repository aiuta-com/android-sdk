package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import coil.compose.rememberAsyncImagePainter
import com.aiuta.fashionsdk.tryon.compose.R

@Composable
internal fun DefaultImage(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        imageVector = ImageVector.vectorResource(R.drawable.demo_image_selector),
        contentDescription = null,
        contentScale = ContentScale.Fit,
    )
}

@Composable
internal fun UploadImage(
    modifier: Modifier = Modifier,
    imageUri: String,
) {
    Image(
        modifier = modifier,
        painter = rememberAsyncImagePainter(model = imageUri),
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}

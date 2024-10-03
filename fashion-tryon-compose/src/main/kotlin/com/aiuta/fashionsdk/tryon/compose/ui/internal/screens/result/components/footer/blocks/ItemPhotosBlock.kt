package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress.LoadingProgress
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.FOOTER_FULL_SIZE_SPAN

internal fun LazyGridScope.itemPhotosBlock(modifier: Modifier = Modifier) {
    item(
        key = "ITEM_PHOTOS_BLOCK_KEY",
        span = { GridItemSpan(FOOTER_FULL_SIZE_SPAN) },
        contentType = "ITEM_PHOTOS_BLOCK_TYPE",
    ) {
        ItemPhotosBlock(
            modifier = modifier,
        )
    }
}

@Composable
private fun ItemPhotosBlock(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val controller = LocalController.current
    val theme = LocalTheme.current
    val activeSKUItem = controller.activeSKUItem.value

    val baseSharedModifier =
        Modifier
            .height(226.dp)
            .width(170.dp)
            .clip(theme.shapes.previewImage)
            .background(theme.colors.neutral)

    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        itemsIndexed(
            items = activeSKUItem.imageUrls,
            key = { _, item -> item },
            contentType = { _, _ -> "ITEM_PHOTO_TYPE" },
        ) { index, url ->
            val finalModifier =
                if (index == 0) {
                    baseSharedModifier.padding(24.dp)
                } else {
                    baseSharedModifier
                }

            Box(
                modifier = finalModifier,
                contentAlignment = Alignment.Center,
            ) {
                SubcomposeAsyncImage(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .clip(theme.shapes.previewImage),
                    model =
                        ImageRequest.Builder(context)
                            .data(url)
                            .crossfade(true)
                            .build(),
                    loading = {
                        LoadingProgress(modifier = Modifier.fillMaxSize())
                    },
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
            }
        }
    }
}

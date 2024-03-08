package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.aiuta.fashionsdk.compose.tokens.FashionColor
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.R
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.block.SKUInfo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress.LoadingProgress
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController

private const val FULL_SIZE_SPAN = 2

@Composable
internal fun GenerationMoreBlock(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val generateMoreSKU = controller.activeSKUItem.value.generateMoreSKU.orEmpty()
    val sharedModifier = Modifier.fillMaxWidth().height(300.dp)

    LazyVerticalGrid(
        modifier = modifier.padding(horizontal = 16.dp),
        columns = GridCells.Fixed(FULL_SIZE_SPAN),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item(
            key = "TITLE_ITEM_KEY",
            span = { GridItemSpan(FULL_SIZE_SPAN) },
        ) {
            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.generation_result_more_title),
                style = MaterialTheme.typography.h5,
                color = FashionColor.Black,
            )

            Spacer(Modifier.height(16.dp))
        }

        items(
            items = generateMoreSKU,
            key = { it.uniqueGeneratedId },
            contentType = { it },
        ) { skuItem ->
            GenerationMoreBlockItem(
                modifier = sharedModifier,
                skuItem = skuItem,
            )
        }
    }
}

@Composable
private fun GenerationMoreBlockItem(
    modifier: Modifier = Modifier,
    skuItem: SKUItem,
) {
    val context = LocalContext.current
    val sharedCornerShape = RoundedCornerShape(16.dp)

    Column(
        modifier =
            modifier
                .background(
                    color = FashionColor.White,
                    shape = sharedCornerShape,
                )
                .border(
                    width = 1.dp,
                    color = FashionColor.LightGray,
                    shape = sharedCornerShape,
                )
                .padding(8.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        SubcomposeAsyncImage(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickableUnindicated {
                        // TODO
                    },
            model =
                ImageRequest.Builder(context)
                    .data(skuItem.imageUrls.firstOrNull())
                    .crossfade(true)
                    .build(),
            loading = {
                LoadingProgress(modifier = Modifier.fillMaxSize())
            },
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )

        Spacer(Modifier.height(4.dp))

        SKUInfo(
            modifier = Modifier.weight(1f),
            skuItem = skuItem,
        )
    }
}

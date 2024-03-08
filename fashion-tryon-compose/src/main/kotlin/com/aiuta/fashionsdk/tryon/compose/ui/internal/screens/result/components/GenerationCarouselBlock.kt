package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.aiuta.fashionsdk.compose.tokens.FashionColor
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress.LoadingProgress
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.GenerationResultController
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun GenerationCarouselBlock(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(vertical = 32.dp),
    generationResultController: GenerationResultController,
) {
    val controller = LocalController.current
    val scope = rememberCoroutineScope()
    val skuGenerationStatus =
        controller
            .fashionTryOn()
            .skuGenerationStatus
            .collectAsStateWithLifecycle()

    val sharedModifier =
        Modifier
            .height(96.dp)
            .width(54.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(FashionColor.White)

    with(generationResultController) {
        LaunchedEffect(generationPagerState.settledPage) {
            scope.launch {
                imageCarouselState.scrollToItem(generationPagerState.settledPage)
            }
        }

        LazyColumn(
            modifier = modifier,
            contentPadding = contentPadding,
            state = imageCarouselState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            itemsIndexed(
                items = skuGenerationStatus.value.imageUrls,
                key = { index, _ -> index },
            ) { index, imageUrl ->
                GenerationItem(
                    modifier = sharedModifier,
                    focused =
                        remember(generationPagerState.settledPage) {
                            generationPagerState.settledPage == index
                        },
                    imageUrl = imageUrl,
                    onClick = {
                        scope.launch {
                            generationPagerState.animateScrollToPage(index)
                        }
                    },
                )
            }

            item(
                key = "META_IMAGES_ITEM_KEY",
            ) {
                val lastIndex = generationPagerState.pageCount - 1
                val borderColor by animateColorAsState(
                    targetValue =
                        if (generationPagerState.settledPage == lastIndex) {
                            FashionColor.White
                        } else {
                            FashionColor.White.copy(alpha = 0.2f)
                        },
                    label = "border color",
                )

                MetaImagesContainer(
                    modifier =
                        sharedModifier
                            .border(
                                width = 3.dp,
                                color = borderColor,
                                shape = RoundedCornerShape(16.dp),
                            )
                            .clickableUnindicated {
                                scope.launch {
                                    generationPagerState.animateScrollToPage(
                                        page = lastIndex,
                                    )
                                }
                            },
                )
            }
        }
    }
}

@Composable
private fun GenerationItem(
    modifier: Modifier = Modifier,
    focused: Boolean = false,
    imageUrl: String,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    val borderColor by animateColorAsState(
        targetValue =
            if (focused) {
                FashionColor.White
            } else {
                FashionColor.White.copy(alpha = 0.2f)
            },
        label = "border color",
    )

    SubcomposeAsyncImage(
        modifier =
            modifier
                .border(
                    width = 3.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(16.dp),
                )
                .clickableUnindicated {
                    onClick()
                },
        model =
            ImageRequest.Builder(context)
                .data(imageUrl)
                .crossfade(true)
                .build(),
        loading = {
            LoadingProgress(modifier = Modifier.fillMaxSize())
        },
        contentScale = ContentScale.Crop,
        contentDescription = null,
    )
}

@Composable
private fun MetaImagesContainer(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val imageUrls = controller.activeSKUItem.value.imageUrls

    when {
        imageUrls.size <= 2 -> {
            MetaImagesContainerSmall(modifier = modifier)
        }

        imageUrls.size == 3 -> {
            MetaImagesContainerMedium(modifier = modifier)
        }

        else -> {
            MetaImagesContainerBig(modifier = modifier)
        }
    }
}

@Composable
private fun MetaImagesContainerSmall(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val controller = LocalController.current
    val imageUrls = controller.activeSKUItem.value.imageUrls

    Row(
        modifier = modifier.background(FashionColor.White),
    ) {
        imageUrls.forEach { url ->
            AsyncImage(
                modifier = Modifier.fillMaxHeight().weight(1f),
                model =
                    ImageRequest.Builder(context)
                        .data(url)
                        .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Composable
private fun MetaImagesContainerMedium(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val controller = LocalController.current
    val imageUrls = controller.activeSKUItem.value.imageUrls

    Row(
        modifier = modifier.background(FashionColor.White),
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxHeight().weight(1f),
            model =
                ImageRequest.Builder(context)
                    .data(imageUrls.firstOrNull())
                    .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier.fillMaxHeight().weight(1f),
        ) {
            for (index in 1..imageUrls.lastIndex) {
                AsyncImage(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    model =
                        ImageRequest.Builder(context)
                            .data(imageUrls.getOrNull(index))
                            .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}

@Composable
private fun MetaImagesContainerBig(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val controller = LocalController.current
    val imageUrls = controller.activeSKUItem.value.imageUrls

    val rowSize = 2
    val columnSize = 2

    Row(
        modifier = modifier.background(FashionColor.White),
    ) {
        (0 until rowSize).forEach { rowIndex ->
            Column(
                modifier = Modifier.weight(1f).fillMaxHeight(),
            ) {
                (0 until columnSize).forEach { columnIndex ->
                    AsyncImage(
                        modifier = Modifier.weight(1f).fillMaxWidth(),
                        model =
                            ImageRequest.Builder(context)
                                .data(imageUrls.getOrNull(rowIndex * rowSize + columnIndex))
                                .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
    }
}

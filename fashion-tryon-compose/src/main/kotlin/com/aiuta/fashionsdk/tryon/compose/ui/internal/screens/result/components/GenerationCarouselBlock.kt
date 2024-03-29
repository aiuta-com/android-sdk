package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.aiuta.fashionsdk.analytic.model.ViewGeneratedImage
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.images.ImagesContainer
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress.LoadingProgress
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.subscribeToLoadingOperations
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.subscribeToSuccessOperations
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic.sendViewGeneratedImageEvent
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
    val theme = LocalTheme.current
    val scope = rememberCoroutineScope()

    val successOperations = controller.subscribeToSuccessOperations()
    val successGenerationUrls =
        remember(successOperations.value) {
            successOperations.value.flatMap { it.generatedImageUrls }
        }

    val loadingOperations = controller.subscribeToLoadingOperations()
    val loadingSourceImages =
        remember(loadingOperations.value) {
            loadingOperations.value.map { it.sourceImageUri.toString() }
        }

    val sharedModifier =
        Modifier
            .height(96.dp)
            .width(54.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(theme.colors.background)

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
                items = successGenerationUrls,
//                key = { _, item -> item },
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
                            controller.sendViewGeneratedImageEvent(
                                newIndex = index,
                                type = ViewGeneratedImage.NavigationType.THUMBNAIL,
                            )
                            generationPagerState.animateScrollToPage(index)
                        }
                    },
                )
            }

            itemsIndexed(
                items = loadingSourceImages,
//                key = { _, item -> item },
            ) { index, imageUrl ->
                // TODO Add progress state
                GenerationItem(
                    modifier = sharedModifier,
                    focused =
                        remember(generationPagerState.settledPage) {
                            generationPagerState.settledPage == index
                        },
                    imageUrl = imageUrl,
                    onClick = {
                        scope.launch {
                            controller.sendViewGeneratedImageEvent(
                                newIndex = index,
                                type = ViewGeneratedImage.NavigationType.THUMBNAIL,
                            )
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
                            theme.colors.background
                        } else {
                            theme.colors.background.copy(alpha = 0.2f)
                        },
                    label = "border color",
                )

                ImagesContainer(
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
                    getImageUrls = {
                        controller.activeSKUItem.value.imageUrls
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
    val theme = LocalTheme.current
    val borderColor by animateColorAsState(
        targetValue =
            if (focused) {
                theme.colors.background
            } else {
                theme.colors.background.copy(alpha = 0.2f)
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

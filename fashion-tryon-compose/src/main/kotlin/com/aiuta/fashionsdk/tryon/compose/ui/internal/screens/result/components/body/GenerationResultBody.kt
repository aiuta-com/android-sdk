package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.body

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.zoom.ZoomImageUiModel
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress.ErrorProgress
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress.LoadingProgress
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.body.blocks.ActionBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.body.blocks.FeedbackBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.body.blocks.GenerateMoreBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.GenerationResultController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.openZoomImageScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.MAIN_IMAGE_SIZE
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue

@Composable
internal fun GenerationResultBody(
    modifier: Modifier = Modifier,
    generationResultController: GenerationResultController,
) {
    val controller = LocalController.current
    val pagerState = generationResultController.generationPagerState

    val generations = controller.sessionGenerationInteractor.sessionGenerations

    val horizontalPaddingWeight = 1 - MAIN_IMAGE_SIZE
    val configuration = LocalConfiguration.current
    val contentHorizontalPadding = (configuration.screenWidthDp.dp * horizontalPaddingWeight) / 2

    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        contentPadding = PaddingValues(horizontal = contentHorizontalPadding),
        pageSpacing = 16.dp,
    ) { index ->
        val pageOffset =
            remember {
                derivedStateOf {
                    pagerState.calculateCurrentOffsetForPage(index).absoluteValue
                }
            }

        val alphaItem =
            remember {
                derivedStateOf {
                    (1 - pageOffset.value).coerceAtLeast(0.3f)
                }
            }

        val heightFraction =
            remember {
                derivedStateOf {
                    (1 - pageOffset.value).coerceAtLeast(0.9f)
                }
            }

        PagerItem(
            modifier =
                Modifier
                    .graphicsLayer {
                        alpha = alphaItem.value
                    }
                    .fillMaxWidth()
                    .fillMaxHeight(heightFraction.value),
            imageUrl = generations.getOrNull(index)?.imageUrl,
            itemIndex = index,
            generationResultController = generationResultController,
            pageOffset = pageOffset,
        )
    }
}

@Composable
private fun PagerItem(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    itemIndex: Int,
    generationResultController: GenerationResultController,
    pageOffset: State<Float>,
) {
    val controller = LocalController.current
    val context = LocalContext.current
    val density = LocalDensity.current
    val theme = LocalTheme.current

    val sharedCornerRadius =
        with(density) {
            theme.shapes.mainImage.topStart.toPx(
                Size.Unspecified,
                density,
            ).toDp()
        }

    var parentImageOffset by remember { mutableStateOf(Offset.Unspecified) }
    var imageSize by remember { mutableStateOf(Size.Zero) }

    Box(
        modifier
            .clip(RoundedCornerShape(sharedCornerRadius))
            .background(
                color = theme.colors.background,
                shape = RoundedCornerShape(sharedCornerRadius),
            ),
    ) {
        SubcomposeAsyncImage(
            modifier =
                Modifier
                    .clipToBounds()
                    .fillMaxSize()
                    .onGloballyPositioned { coordinates ->
                        parentImageOffset = coordinates.positionInRoot()
                        imageSize = coordinates.size.toSize()
                    }
                    .clickableUnindicated {
                        controller.zoomImageController.openZoomImageScreen(
                            model =
                                ZoomImageUiModel(
                                    imageSize = imageSize,
                                    initialCornerRadius = sharedCornerRadius,
                                    imageUrl = imageUrl,
                                    parentImageOffset = parentImageOffset,
                                    additionalShareInfo = controller.activeSKUItem.value.additionalShareInfo,
                                ),
                        )
                    },
            model =
                ImageRequest.Builder(context)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
            loading = { LoadingProgress(modifier = Modifier.fillMaxSize()) },
            error = { ErrorProgress(modifier = Modifier.fillMaxSize()) },
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )

        PagerItemInterface(
            modifier = Modifier.fillMaxSize(),
            imageUrl = imageUrl,
            itemIndex = itemIndex,
            generationResultController = generationResultController,
            pageOffset = pageOffset,
        )
    }
}

@Composable
internal fun BoxScope.PagerItemInterface(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    itemIndex: Int,
    generationResultController: GenerationResultController,
    pageOffset: State<Float>,
) {
    val isVisible =
        remember {
            derivedStateOf {
                pageOffset.value == 0f
            }
        }

    AnimatedVisibility(
        modifier = modifier,
        visible = isVisible.value,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            ActionBlock(
                modifier =
                    Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp),
                imageUrl = imageUrl,
            )

            GenerateMoreBlock(
                modifier =
                    Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp),
            )

            FeedbackBlock(
                modifier =
                    Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp),
                itemIndex = itemIndex,
                generationResultController = generationResultController,
            )
        }
    }
}

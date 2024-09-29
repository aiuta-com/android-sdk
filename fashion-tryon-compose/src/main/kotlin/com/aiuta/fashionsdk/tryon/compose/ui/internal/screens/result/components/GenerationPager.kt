package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.aiuta.fashionsdk.compose.tokens.FashionIcon
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.zoom.ZoomImageUiModel
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress.LoadingProgress
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.isActiveSKUGenerateMoreNotEmpty
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.subscribeToSuccessOperations
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.GenerationResultController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.isGenerationPagerItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.isMetaInfoPagerItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.openZoomImageScreen

private val horizontalPadding = 32.dp
private val sharedModifier =
    Modifier
        .fillMaxSize()
        .padding(horizontal = horizontalPadding)

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun GenerationVerticalPagerBlock(
    modifier: Modifier = Modifier,
    generationResultController: GenerationResultController,
) {
    val controller = LocalController.current

    val successOperations = controller.subscribeToSuccessOperations()
    val generationUrls =
        remember(successOperations.value) {
            successOperations.value.flatMap { it.generatedImageUrls }
        }

    VerticalPager(
        modifier = modifier,
        state = generationResultController.generationPagerState,
        contentPadding = PaddingValues(vertical = 16.dp),
        userScrollEnabled = !generationResultController.verticalSwipeState.isAnimationRunning,
        pageSpacing = 8.dp,
    ) { index ->
        when {
            generationResultController.isGenerationPagerItem(index) -> {
                // Just generation
                PagerImageContainer(
                    modifier = sharedModifier,
                    imageUrl = generationUrls.getOrNull(index),
                    itemIndex = index,
                    generationResultController = generationResultController,
                )
            }

            generationResultController.isMetaInfoPagerItem(index) -> {
                // SKU Meta pages
                HorizontalMetaPager(
                    modifier = Modifier.fillMaxSize(),
                    generationResultController = generationResultController,
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun HorizontalMetaPager(
    modifier: Modifier = Modifier,
    generationResultController: GenerationResultController,
) {
    val controller = LocalController.current
    val skuMetaImages = controller.activeSKUItem.value.imageUrls

    val pagerState =
        rememberPagerState {
            skuMetaImages.size
        }

    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        contentPadding = PaddingValues(horizontal = horizontalPadding),
        pageSpacing = 8.dp,
    ) { index ->
        PagerImageContainer(
            modifier = Modifier.fillMaxSize(),
            imageUrl = skuMetaImages.getOrNull(index),
            isShareAvailable = false,
            isSwipeTipVisible = controller.isActiveSKUGenerateMoreNotEmpty().value,
            itemIndex = index,
            generationResultController = generationResultController,
        )
    }
}

@Composable
internal fun PagerImageContainer(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    isShareAvailable: Boolean = true,
    isSwipeTipVisible: Boolean = false,
    itemIndex: Int,
    generationResultController: GenerationResultController,
) {
    val controller = LocalController.current
    val context = LocalContext.current
    val theme = LocalTheme.current
    val sharedCornerRadius = 24.dp

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
            loading = {
                LoadingProgress(modifier = Modifier.fillMaxSize())
            },
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )

        if (isSwipeTipVisible) {
            PagerImageSwipeTip(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
            )
        }

        if (isShareAvailable) {
            ShareButton(
                modifier =
                    Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                        .size(38.dp)
                        .shadow(
                            elevation = 4.dp,
                            spotColor = Color.Black.copy(0.2f),
                            ambientColor = Color.Black.copy(0.2f),
                        ),
                imageUrl = imageUrl,
            )
        }

        FeedbackBlock(
            modifier =
                Modifier
                    .align(Alignment.BottomEnd)
                    .padding(
                        horizontal = 8.dp,
                        vertical = 12.dp,
                    ),
            isSwipeTipVisible = isSwipeTipVisible,
            itemIndex = itemIndex,
            generationResultController = generationResultController,
        )
    }
}

@Composable
private fun PagerImageSwipeTip(modifier: Modifier = Modifier) {
    val stringResources = LocalAiutaTryOnStringResources.current

    Column(
        modifier =
            modifier.background(
                brush =
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Black,
                        ),
                    ),
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(FashionIcon.SwipeUp),
            contentDescription = null,
            tint = Color.White,
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = stringResources.generationResultSwipeUp,
            style = MaterialTheme.typography.h6,
            color = Color.White,
        )

        Spacer(Modifier.height(24.dp))
    }
}

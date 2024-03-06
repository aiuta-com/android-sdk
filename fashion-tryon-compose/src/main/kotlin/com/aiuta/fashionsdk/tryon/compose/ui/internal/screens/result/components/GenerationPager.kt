package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.aiuta.fashionsdk.compose.tokens.FashionColor
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.domain.models.ZoomImageUiModel
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress.LoadingProgress
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.GenerationResultController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.isGenerationPagerItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.isMetaInfoPagerItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.openZoomImageScreen

private val horizontalPadding = 32.dp
private val sharedModifier = Modifier.fillMaxSize().padding(horizontal = horizontalPadding)

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun GenerationVerticalPagerBlock(
    modifier: Modifier = Modifier,
    generationResultController: GenerationResultController,
) {
    val controller = LocalController.current
    val skuGenerationStatus =
        controller
            .fashionTryOn()
            .skuGenerationStatus
            .collectAsStateWithLifecycle()

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
                    imageUrl = skuGenerationStatus.value.imageUrls.getOrNull(index),
                )
            }

            generationResultController.isMetaInfoPagerItem(index) -> {
                // SKU Meta pages
                HorizontalMetaPager(
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun HorizontalMetaPager(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val skuMetaImages = controller.skuMetaInfo().imageUrls

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
        )
    }
}

@Composable
internal fun PagerImageContainer(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    isShareAvailable: Boolean = true,
) {
    val controller = LocalController.current
    val context = LocalContext.current
    val sharedCornerRadius = 24.dp

    var parentImageOffset by remember { mutableStateOf(Offset.Unspecified) }
    var imageSize by remember { mutableStateOf(Size.Zero) }

    Box(
        modifier
            .clip(RoundedCornerShape(sharedCornerRadius))
            .background(
                color = FashionColor.White,
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

        if (isShareAvailable) {
            ShareButton(
                modifier =
                    Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                        .size(38.dp)
                        .shadow(
                            elevation = 4.dp,
                            spotColor = FashionColor.Black.copy(0.2f),
                            ambientColor = FashionColor.Black.copy(0.2f),
                        ),
                imageUrl = imageUrl,
            )
        }
    }
}

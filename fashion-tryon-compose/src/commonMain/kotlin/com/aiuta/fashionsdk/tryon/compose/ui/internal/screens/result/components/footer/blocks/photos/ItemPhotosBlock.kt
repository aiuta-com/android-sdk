package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.blocks.photos

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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.BottomSheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.util.lerp
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.zoom.ZoomImageUiModel
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress.ErrorProgress
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress.LoadingProgress
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.FOOTER_FULL_SIZE_SPAN
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.GenerationResultController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.openZoomImageScreen

internal fun LazyGridScope.itemPhotosBlock(
    modifier: Modifier = Modifier,
    generationResultController: GenerationResultController,
) {
    item(
        key = "ITEM_PHOTOS_BLOCK_KEY",
        span = { GridItemSpan(FOOTER_FULL_SIZE_SPAN) },
        contentType = "ITEM_PHOTOS_BLOCK_TYPE",
    ) {
        ItemPhotosBlock(
            modifier = modifier,
            generationResultController = generationResultController,
        )
    }
}

@Composable
private fun ItemPhotosBlock(
    modifier: Modifier = Modifier,
    generationResultController: GenerationResultController,
) {
    val coilContext = LocalPlatformContext.current
    val controller = LocalController.current
    val density = LocalDensity.current
    val theme = LocalTheme.current

    val activeSKUItem = controller.activeSKUItem.value

    val sharedRadius =
        with(density) {
            theme.shapes.previewImage.topStart.toPx(Size.Unspecified, density).toDp()
        }

    val bottomSheetState =
        generationResultController
            .bottomSheetScaffoldState
            .bottomSheetState
    val sheetProgress =
        bottomSheetState.progress(
            from = BottomSheetValue.Collapsed,
            to = BottomSheetValue.Expanded,
        )

    val alphaRow =
        remember(sheetProgress) {
            derivedStateOf {
                lerp(
                    start = 0.1f,
                    stop = 1f,
                    fraction = sheetProgress,
                )
            }
        }

    val sharedSharedModifier =
        Modifier
            .height(226.dp)
            .width(170.dp)
            .clip(theme.shapes.previewImage)
            .background(theme.colors.neutral)
    val sharedImageModifier = Modifier.fillMaxSize().clip(theme.shapes.previewImage)

    LazyRow(
        modifier = modifier.alpha(alphaRow.value),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        itemsIndexed(
            items = activeSKUItem.imageUrls,
            key = { _, item -> item },
            contentType = { _, _ -> "ITEM_PHOTO_TYPE" },
        ) { index, url ->
            var parentImageOffset by remember { mutableStateOf(Offset.Unspecified) }
            var imageSize by remember { mutableStateOf(Size.Zero) }

            val finalImageModifier =
                if (theme.toggles.isProductFistImageExtendedPaddingApplied && index == 0) {
                    sharedImageModifier.padding(
                        top = 24.dp,
                        start = 32.dp,
                        end = 32.dp,
                        bottom = 40.dp,
                    )
                } else {
                    sharedImageModifier
                }

            Box(
                modifier = sharedSharedModifier,
                contentAlignment = Alignment.Center,
            ) {
                SubcomposeAsyncImage(
                    modifier =
                        finalImageModifier
                            .onGloballyPositioned { coordinates ->
                                parentImageOffset = coordinates.positionInRoot()
                                imageSize = coordinates.size.toSize()
                            }
                            .clickableUnindicated {
                                controller.zoomImageController.openZoomImageScreen(
                                    model =
                                        ZoomImageUiModel(
                                            imageSize = imageSize,
                                            initialCornerRadius = sharedRadius,
                                            imageUrl = url,
                                            parentImageOffset = parentImageOffset,
                                            additionalShareInfo = controller.activeSKUItem.value.additionalShareInfo,
                                            originPageId = AiutaAnalyticPageId.RESULTS,
                                        ),
                                )
                            },
                    model =
                        ImageRequest.Builder(coilContext)
                            .data(url)
                            .crossfade(true)
                            .build(),
                    loading = { LoadingProgress(modifier = Modifier.fillMaxSize()) },
                    error = { ErrorProgress(modifier = Modifier.fillMaxSize()) },
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
            }
        }
    }
}

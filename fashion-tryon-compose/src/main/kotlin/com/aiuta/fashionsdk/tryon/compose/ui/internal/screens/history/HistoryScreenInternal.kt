package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.aiuta.fashionsdk.compose.molecules.images.AiutaIcon
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsHistoryEventType
import com.aiuta.fashionsdk.internal.analytic.model.ShareGeneratedImage
import com.aiuta.fashionsdk.tryon.compose.domain.internal.share.ShareManager
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.zoom.ZoomImageUiModel
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendPageEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendShareGeneratedImageEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress.LoadingProgress
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.deactivateSelectMode
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.isSelectModeActive
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.analytic.sendHistoryEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.analytic.sendOpenHistoryEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.components.SelectorCard
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.components.common.HistoryAppBar
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.models.SelectorMode
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.utils.deleteGeneratedImages
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.openZoomImageScreen

private const val FULL_SIZE_SPAN = 3

@Composable
internal fun HistoryScreen(modifier: Modifier = Modifier) {
    val theme = LocalTheme.current

    sendPageEvent(pageId = AiutaAnalyticPageId.HISTORY)

    Column(
        modifier = modifier.background(theme.colors.background),
    ) {
        HistoryAppBar(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
        )

        Spacer(Modifier.height(16.dp))

        HistoryScreenInternal(
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
private fun HistoryScreenInternal(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val density = LocalDensity.current
    val theme = LocalTheme.current

    val generatedImages =
        controller
            .generatedImageInteractor
            .generatedImagesFlow()
            .collectAsLazyPagingItems()

    val sharedRadius =
        with(density) {
            theme.shapes.previewImage.topStart.toPx(
                Size.Unspecified,
                density,
            ).toDp()
        }

    sendOpenHistoryEvent()

    Box(
        modifier =
            modifier
                .fillMaxSize()
                .background(color = theme.colors.background),
    ) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(FULL_SIZE_SPAN),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                span = { GridItemSpan(1) },
                count = generatedImages.itemCount,
                key = { generatedImages[it]?.id ?: 0 },
                contentType = { generatedImages.itemSnapshotList.getOrNull(it) },
            ) { index ->
                val generatedImage = generatedImages[index]
                val isSelectModeActive = controller.isSelectModeActive()

                var parentImageOffset by remember { mutableStateOf(Offset.Unspecified) }
                var imageSize by remember { mutableStateOf(Size.Zero) }

                ImageContainer(
                    modifier =
                        Modifier
                            .onGloballyPositioned { coordinates ->
                                parentImageOffset = coordinates.positionInRoot()
                                imageSize = coordinates.size.toSize()
                            },
                    imageUrl = generatedImage?.imageUrl,
                    isEdit = isSelectModeActive.value,
                    isSelectedItem = controller.selectorHolder.contain(generatedImage),
                    onClick = {
                        when {
                            isSelectModeActive.value -> {
                                generatedImage?.let {
                                    controller.selectorHolder.putOrRemove(generatedImage)
                                }
                            }

                            else -> {
                                controller.zoomImageController.openZoomImageScreen(
                                    model =
                                        ZoomImageUiModel(
                                            imageSize = imageSize,
                                            initialCornerRadius = sharedRadius,
                                            imageUrl = generatedImage?.imageUrl,
                                            parentImageOffset = parentImageOffset,
                                        ),
                                )
                            }
                        }
                    },
                )
            }
        }

        HistoryScreenEmpty(
            modifier = Modifier.fillMaxSize(),
            getGeneratedImages = { generatedImages },
        )

        HistoryScreenInterface(
            getGeneratedImages = { generatedImages },
        )
    }
}

@Composable
private fun ImageContainer(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    isEdit: Boolean = false,
    isSelectedItem: Boolean = false,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    val theme = LocalTheme.current

    Box(
        modifier = modifier.clickableUnindicated { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        SubcomposeAsyncImage(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(178.dp)
                    .clip(theme.shapes.previewImage)
                    .background(color = theme.colors.background),
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

        AnimatedVisibility(
            modifier =
                Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp),
            visible = isEdit,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            Box(
                modifier =
                    Modifier
                        .size(24.dp)
                        .border(width = 1.dp, color = theme.colors.onDark, shape = CircleShape)
                        .background(
                            color = if (isSelectedItem) theme.colors.aiuta else theme.colors.neutral,
                            shape = CircleShape,
                        ),
                contentAlignment = Alignment.Center,
            ) {
                if (isSelectedItem) {
                    AiutaIcon(
                        modifier = Modifier.size(16.dp),
                        icon = theme.icons.check16,
                        contentDescription = null,
                        tint = theme.colors.onDark,
                    )
                }
            }
        }
    }
}

@Composable
private fun BoxScope.HistoryScreenInterface(
    getGeneratedImages: () -> LazyPagingItems<GeneratedImage>,
) {
    val controller = LocalController.current
    val theme = LocalTheme.current
    val scope = rememberCoroutineScope()
    val generatedImages = getGeneratedImages()

    AnimatedVisibility(
        modifier =
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.navigationBars)
                .padding(horizontal = 8.dp),
        visible = controller.isSelectModeActive().value,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        val context = LocalContext.current

        SelectorCard(
            modifier = Modifier.fillMaxWidth(),
            selectionMode = controller.selectorState,
            isActionActive = !controller.selectorHolder.isEmpty(),
            onSelectAll = {
                controller.selectorHolder.reset(generatedImages.itemSnapshotList.items)
                controller.selectorState.value = SelectorMode.ALL_IS_SELECTED
            },
            onDeselectAll = {
                controller.selectorHolder.removeAll()
                controller.selectorState.value = SelectorMode.ALL_IS_NOT_SELECTED
            },
            onCancel = {
                controller.deactivateSelectMode()
            },
            onShare = {
                val shareManager = ShareManager(context)
                val imageUrls =
                    controller
                        .selectorHolder
                        .getList()
                        .map { it.imageUrl }

                controller.sendHistoryEvent(AiutaAnalyticsHistoryEventType.GENERATED_IMAGE_SHARED)
                controller.sendShareGeneratedImageEvent(
                    origin = ShareGeneratedImage.Origin.HISTORY_SCREEN,
                    count = imageUrls.size,
                )
                shareManager.share(
                    imageUrls = imageUrls,
                    watermark = theme.watermark,
                    origin = ShareGeneratedImage.Origin.HISTORY_SCREEN,
                )

                controller.deactivateSelectMode()
            },
            onDelete = {
                controller.sendHistoryEvent(AiutaAnalyticsHistoryEventType.GENERATED_IMAGE_DELETED)
                controller.deleteGeneratedImages(scope)
            },
        )
    }
}

@Composable
private fun BoxScope.HistoryScreenEmpty(
    modifier: Modifier = Modifier,
    getGeneratedImages: () -> LazyPagingItems<GeneratedImage>,
) {
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    val generatedImages = getGeneratedImages()

    if (generatedImages.itemCount == 0) {
        Column(
            modifier = modifier.background(theme.colors.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AiutaIcon(
                modifier = Modifier.size(100.dp),
                icon = theme.icons.recent100,
                contentDescription = null,
                tint = theme.colors.tertiary,
            )

            Spacer(Modifier.height(36.dp))

            Text(
                modifier = Modifier.padding(horizontal = 30.dp),
                text = stringResources.historyEmptyDescription,
                style = theme.typography.regular,
                color = theme.colors.primary,
                textAlign = TextAlign.Center,
            )
        }
    }
}

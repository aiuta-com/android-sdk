package com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.operations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.aiuta.fashionsdk.compose.molecules.button.FashionButton
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonSizes
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonStyles
import com.aiuta.fashionsdk.compose.molecules.images.AiutaIcon
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsPickerEventType
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.operations.LocalGeneratedOperationInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.operations.cleanLoadingUploads
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.dataprovider.AiutaHistoryImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperationUIModel
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendPickerAnalytic
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress.ErrorProgress
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress.LoadingProgress
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.activateAutoTryOn
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnLoadingActionsController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.updateActiveOperationOrSetEmpty
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.components.SheetDivider
import com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.operations.controller.GeneratedOperationsSheetListener
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.paging.collectAsLazyPagingItems
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.paging.itemContentType
import kotlinx.coroutines.launch

@Composable
internal fun ColumnScope.GeneratedOperationsSheet() {
    val aiutaConfiguration = LocalAiutaConfiguration.current
    val controller = LocalController.current
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    val sharedHorizontalPadding = 16.dp
    val sharedOperationsModifier =
        Modifier
            .width(148.dp)
            .height(254.dp)
            .clip(theme.shapes.previewImage)

    val generatedOperations =
        controller.generatedOperationInteractor
            .getGeneratedOperationFlow()
            .collectAsLazyPagingItems()

    sendPickerAnalytic(
        event = AiutaAnalyticsPickerEventType.UPLOADS_HISTORY_OPENED,
        pageId = AiutaAnalyticPageId.IMAGE_PICKER,
    )

    GeneratedOperationsSheetListener()

    SheetDivider()

    Spacer(Modifier.height(24.dp))

    Text(
        modifier = Modifier.padding(horizontal = sharedHorizontalPadding),
        text = stringResources.generatedOperationsSheetPreviously,
        style = theme.typography.titleM,
        color = theme.colors.primary,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )

    Spacer(Modifier.height(24.dp))

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = sharedHorizontalPadding),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            count = generatedOperations.itemCount,
            key = { generatedOperations[it]?.operationId ?: 0 },
            contentType = generatedOperations.itemContentType { it },
        ) {
            val generatedOperation = generatedOperations[it]

            generatedOperation?.let {
                OperationItem(
                    modifier = sharedOperationsModifier.animateItem(),
                    generatedOperation = generatedOperation,
                    onClick = {
                        with(controller) {
                            // Analytic
                            sendPickerAnalytic(
                                event = AiutaAnalyticsPickerEventType.UPLOADED_PHOTO_SELECTED,
                                pageId = AiutaAnalyticPageId.IMAGE_PICKER,
                            )

                            // Host notification
                            val image = generatedOperation.urlImages.firstOrNull()
                            image?.let {
                                aiutaConfiguration.dataProvider?.selectUploadedImageAction?.invoke(
                                    AiutaHistoryImage(id = image.imageId, url = image.imageUrl),
                                )
                            }

                            // Change
                            updateActiveOperationOrSetEmpty(generatedOperation)
                            // Activate auto try on
                            activateAutoTryOn()
                            // Move back
                            bottomSheetNavigator.hide()
                        }
                    },
                )
            }
        }
    }

    Spacer(Modifier.height(24.dp))

    FashionButton(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = sharedHorizontalPadding),
        text =
            if (aiutaConfiguration.toggles.isTryonWithModelsAvailable) {
                stringResources.generatedOperationsSheetUploadNewButtonWithModels
            } else {
                stringResources.generatedOperationsSheetUploadNewButton
            },
        style = FashionButtonStyles.primaryStyle(theme),
        size = FashionButtonSizes.lSize(),
        onClick = {
            controller.bottomSheetNavigator.change(
                newSheetScreen =
                    NavigationBottomSheetScreen.ImagePicker(
                        originPageId = AiutaAnalyticPageId.IMAGE_PICKER,
                    ),
            )
        },
    )
}

@Composable
private fun OperationItem(
    modifier: Modifier = Modifier,
    generatedOperation: GeneratedOperationUIModel,
    onClick: () -> Unit,
) {
    val coilContext = LocalPlatformContext.current
    val controller = LocalController.current
    val loadingActionsController = LocalAiutaTryOnLoadingActionsController.current
    val theme = LocalTheme.current
    val scope = rememberCoroutineScope()

    val imageState =
        remember {
            mutableStateOf<AsyncImagePainter.State>(AsyncImagePainter.State.Empty)
        }

    val isLoadingScrimVisible =
        remember {
            derivedStateOf {
                loadingActionsController.loadingUploadsHolder.contain(generatedOperation)
            }
        }

    val isTrashVisible =
        remember {
            derivedStateOf {
                !isLoadingScrimVisible.value
            }
        }

    Box(
        modifier = modifier.clickableUnindicated(!isLoadingScrimVisible.value) { onClick() },
    ) {
        SubcomposeAsyncImage(
            modifier =
                Modifier
                    .clipToBounds()
                    .fillMaxSize(),
            model =
                ImageRequest.Builder(coilContext)
                    .data(generatedOperation.sourceImageUrls.firstOrNull())
                    .crossfade(true)
                    .build(),
            loading = { LoadingProgress(modifier = Modifier.fillMaxSize()) },
            error = { ErrorProgress(modifier = Modifier.fillMaxSize()) },
            onError = { imageState.value = it },
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )

        AnimatedVisibility(
            modifier =
                Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp),
            visible = isTrashVisible.value,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            AiutaIcon(
                modifier =
                    Modifier
                        .size(24.dp)
                        .clickableUnindicated {
                            scope.launch {
                                with(controller) {
                                    sendPickerAnalytic(
                                        event = AiutaAnalyticsPickerEventType.UPLOADED_PHOTO_DELETED,
                                        pageId = AiutaAnalyticPageId.IMAGE_PICKER,
                                    )

                                    // Add operation to loading list
                                    loadingActionsController.loadingUploadsHolder.put(
                                        generatedOperation,
                                    )

                                    // Delete operations
                                    generatedOperationInteractor.deleteOperation(generatedOperation)
                                    // If local mode - let's remove from loading
                                    generatedOperationInteractor.cleanLoadingUploads(
                                        cleanAction = {
                                            loadingActionsController.loadingUploadsHolder.remove(
                                                generatedOperation,
                                            )
                                        },
                                    )

                                    // If active images is deleted and it's local mode - let's get new first
                                    val isLocalMode =
                                        generatedOperationInteractor is LocalGeneratedOperationInteractor
                                    val isActiveOperationDeleted =
                                        lastSavedOperation.value?.operationId == generatedOperation.operationId
                                    if (isActiveOperationDeleted && isLocalMode) {
                                        // Try to get new first
                                        val newFirstOperation =
                                            generatedOperationInteractor
                                                .getFirstGeneratedOperation()

                                        // Try to update with new or set as empty
                                        updateActiveOperationOrSetEmpty(newFirstOperation)
                                    }
                                }
                            }
                        },
                icon = theme.icons.trash24,
                contentDescription = null,
                tint = theme.colors.background,
            )
        }

        AnimatedVisibility(
            modifier =
                Modifier
                    .clipToBounds()
                    .fillMaxSize(),
            visible = isLoadingScrimVisible.value,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            LoadingProgress(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f)),
                circleColor = Color.White,
            )
        }
    }
}

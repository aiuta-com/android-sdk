package com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.operations

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.aiuta.fashionsdk.compose.molecules.button.FashionButton
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonSizes
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonStyles
import com.aiuta.fashionsdk.compose.molecules.images.AiutaIcon
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsPickerEventType
import com.aiuta.fashionsdk.tryon.compose.domain.models.dataprovider.AiutaUploadedImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.LastSavedImages
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.toLastSavedImages
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperation
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendPickerAnalytic
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.images.ImagesContainer
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress.LoadingProgress
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.components.SheetDivider
import com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.operations.analytic.sendSelectOldPhotos
import com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.operations.controller.GeneratedOperationsSheetListener
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ColumnScope.GeneratedOperationsSheet() {
    val aiutaConfiguration = LocalAiutaConfiguration.current
    val controller = LocalController.current
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    val sharedHorizontalPadding = 16.dp
    val sharedOperationsModifier =
        Modifier
            .width(150.dp)
            .height(254.dp)
            .clip(theme.shapes.previewImage)
            .border(
                width = 1.dp,
                color = Color(0xFFEEEEEE),
                shape = theme.shapes.previewImage,
            )

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
    ) {
        items(
            count = generatedOperations.itemCount,
            key = { generatedOperations[it]?.operationId ?: 0 },
            contentType = generatedOperations.itemContentType { it },
        ) {
            val generatedOperation = generatedOperations[it]

            generatedOperation?.let {
                OperationItem(
                    modifier = sharedOperationsModifier.animateItemPlacement(),
                    generatedOperation = generatedOperation,
                    onClick = {
                        with(controller) {
                            // Analytic
                            sendSelectOldPhotos(generatedOperation.sourceImageUrls.size)
                            sendPickerAnalytic(
                                event = AiutaAnalyticsPickerEventType.UPLOADED_PHOTO_SELECTED,
                                pageId = AiutaAnalyticPageId.IMAGE_PICKER,
                            )

                            // Host notification
                            val image = generatedOperation.sourceImages.firstOrNull()
                            image?.let {
                                aiutaConfiguration.dataProvider?.selectUploadedImageAction?.invoke(
                                    AiutaUploadedImage(id = image.imageId, url = image.imageUrl),
                                )
                            }

                            // Change
                            lastSavedOperation.value = generatedOperation
                            lastSavedImages.value = generatedOperation.toLastSavedImages()
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
        text = stringResources.generatedOperationsSheetUploadNewButton,
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
    generatedOperation: GeneratedOperation,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    val controller = LocalController.current
    val theme = LocalTheme.current
    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier.clickableUnindicated { onClick() },
    ) {
        if (generatedOperation.sourceImageUrls.size == 1) {
            SubcomposeAsyncImage(
                modifier =
                    Modifier
                        .clipToBounds()
                        .fillMaxSize(),
                model =
                    ImageRequest.Builder(context)
                        .data(generatedOperation.sourceImageUrls.firstOrNull())
                        .crossfade(true)
                        .build(),
                loading = {
                    LoadingProgress(modifier = Modifier.fillMaxSize())
                },
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        } else {
            ImagesContainer(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .clip(theme.shapes.previewImage),
                getImageUrls = { generatedOperation.sourceImageUrls },
            )
        }

        AiutaIcon(
            modifier =
                Modifier
                    .align(Alignment.BottomEnd)
                    .padding(
                        end = 6.dp,
                        bottom = 8.dp,
                    )
                    .size(24.dp)
                    .clickableUnindicated {
                        scope.launch {
                            with(controller) {
                                sendPickerAnalytic(
                                    event = AiutaAnalyticsPickerEventType.UPLOADED_PHOTO_DELETED,
                                    pageId = AiutaAnalyticPageId.IMAGE_PICKER,
                                )
                                generatedOperationInteractor.deleteOperation(generatedOperation)
                                if (lastSavedOperation.value == generatedOperation) {
                                    lastSavedOperation.value = null
                                    lastSavedImages.value = LastSavedImages.Empty
                                }
                            }
                        }
                    },
            icon = theme.icons.trash24,
            contentDescription = null,
            tint = theme.colors.background,
        )
    }
}

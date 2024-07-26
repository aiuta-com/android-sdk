package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aiuta.fashionsdk.compose.molecules.button.FashionButton
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonSizes
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonStyles
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.isLastSavedPhotoAvailable
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.analytic.sendTapChangePhotoEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.models.ImageSelectorState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.transitionAnimation

@Composable
internal fun ImageSelectorBottom(
    modifier: Modifier = Modifier,
    uploadPhoto: () -> Unit,
) {
    val controller = LocalController.current
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    val generationStatus = controller.generationStatus
    val countGeneratedOperation =
        controller.generatedOperationInteractor
            .countGeneratedOperation()
            .collectAsStateWithLifecycle(0)

    val sharedModifier = Modifier.fillMaxWidth()
    val sharedBackground = Color.White.copy(alpha = 0.5f)
    val sharedCornerSize = RoundedCornerShape(8.dp)
    val sharedButtonSize =
        FashionButtonSizes.xlSize(
            shape = sharedCornerSize,
            verticalPadding = 12.dp,
            horizontalPadding = 20.dp,
        )

    val bottomState =
        updateTransition(
            targetState =
                when {
                    generationStatus.value == SKUGenerationUIStatus.LOADING -> {
                        ImageSelectorState.GENERATION_LOADING
                    }

                    controller.isLastSavedPhotoAvailable().value -> {
                        ImageSelectorState.LAST_IMAGE_SAVED
                    }

                    else -> ImageSelectorState.IDLE
                },
            label = "bottomState",
        )

    bottomState.AnimatedContent(
        modifier = modifier,
        transitionSpec = { transitionAnimation },
    ) { state ->
        when (state) {
            ImageSelectorState.IDLE -> {
                FashionButton(
                    modifier = sharedModifier,
                    text = stringResources.imageSelectorUploadButton,
                    style = FashionButtonStyles.primaryStyle(theme),
                    size = sharedButtonSize,
                    onClick = {
                        controller.sendTapChangePhotoEvent()
                        uploadPhoto()
                    },
                )
            }

            ImageSelectorState.LAST_IMAGE_SAVED -> {
                FashionButton(
                    modifier = sharedModifier,
                    text = stringResources.imageSelectorChangeButton,
                    style =
                        FashionButtonStyles.primaryStyle(
                            backgroundColor = sharedBackground,
                            contentColor = Color.Black,
                        ),
                    size = sharedButtonSize,
                    onClick = {
                        if (countGeneratedOperation.value == 0) {
                            controller.sendTapChangePhotoEvent()
                            uploadPhoto()
                        } else {
                            controller.sendTapChangePhotoEvent(isHistorySheetOpened = true)
                            controller.bottomSheetNavigator.show(
                                NavigationBottomSheetScreen.GeneratedOperations,
                            )
                        }
                    },
                )
            }

            ImageSelectorState.GENERATION_LOADING -> {
                Row(
                    modifier =
                        sharedModifier
                            .background(
                                color = sharedBackground,
                                shape = sharedCornerSize,
                            )
                            .padding(
                                horizontal = 20.dp,
                                vertical = 12.dp,
                            ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = theme.colors.primary,
                        strokeWidth = 2.dp,
                    )

                    Spacer(Modifier.width(8.dp))

                    Text(
                        text = stringResources.imageSelectorGeneratingOutfit,
                        style = MaterialTheme.typography.body1,
                        color = theme.colors.primary,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

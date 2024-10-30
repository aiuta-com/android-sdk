package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components.body

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aiuta.fashionsdk.compose.molecules.button.FashionButton
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonSizes
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonStyles
import com.aiuta.fashionsdk.compose.molecules.images.AiutaIcon
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
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

    val sharedModifier = Modifier.wrapContentWidth()
    val sharedBackground = Color.White.copy(alpha = 0.4f)

    val sharedButtonSize = FashionButtonSizes.mSize()

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
                        if (theme.toggles.isBlurOutlinesEnabled) {
                            FashionButtonStyles.secondaryStyle(
                                backgroundColor = sharedBackground,
                                contentColor = theme.colors.onDark,
                                borderColor = theme.colors.neutral2,
                            )
                        } else {
                            FashionButtonStyles.primaryStyle(
                                backgroundColor = sharedBackground,
                                contentColor = Color.Black,
                            )
                        },
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
                val finalModifier =
                    if (theme.toggles.isBlurOutlinesEnabled) {
                        sharedModifier.border(
                            width = 1.dp,
                            color = theme.colors.neutral2,
                            shape = sharedButtonSize.shape,
                        )
                    } else {
                        sharedModifier
                    }

                Row(
                    modifier =
                        finalModifier
                            .background(
                                color = sharedBackground,
                                shape = sharedButtonSize.shape,
                            )
                            .padding(
                                horizontal = 24.dp,
                                vertical = 12.dp,
                            ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    val infiniteTransition = rememberInfiniteTransition()
                    val angle =
                        infiniteTransition.animateFloat(
                            initialValue = 0F,
                            targetValue = 360F,
                            animationSpec =
                                infiniteRepeatable(
                                    animation = tween(2000, easing = LinearEasing),
                                ),
                        )
                    val activeColor =
                        if (theme.toggles.isBlurOutlinesEnabled)
                            {
                                theme.colors.onDark
                            } else {
                            theme.colors.primary
                        }

                    AiutaIcon(
                        modifier =
                            Modifier
                                .size(14.dp)
                                .rotate(angle.value),
                        icon = theme.icons.loading14,
                        tint = activeColor,
                        contentDescription = null,
                    )

                    Spacer(Modifier.width(8.dp))

                    Text(
                        text = stringResources.imageSelectorGeneratingOutfit,
                        style = theme.typography.smallButton,
                        color = activeColor,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

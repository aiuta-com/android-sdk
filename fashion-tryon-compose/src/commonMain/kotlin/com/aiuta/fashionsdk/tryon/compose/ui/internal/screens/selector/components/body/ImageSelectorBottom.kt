package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components.body

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.molecules.button.FashionButton
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonSizes
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonStyles
import com.aiuta.fashionsdk.compose.molecules.images.AiutaIcon
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.AiutaImageSelectorFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.buttons.ButtonsMode
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.models.ImageSelectorState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.utils.solveLoadingGenerationText
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.strictProvideFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.transitionAnimation
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeChild

@Composable
internal fun ImageSelectorBottom(
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    uploadPhoto: () -> Unit,
) {
    val controller = LocalController.current
    val theme = LocalTheme.current

    val imageSelectorFeature = strictProvideFeature<AiutaImageSelectorFeature>()

    val generationStatus = controller.generationStatus
    val countGeneratedOperation =
        controller.generatedOperationInteractor
            .countGeneratedOperation()
            .collectAsState(0)

    val sharedModifier = Modifier.wrapContentWidth()
    val sharedButtonSize = FashionButtonSizes.mSize()

    val sharedColor = theme.colors.background.copy(alpha = 0.4f)
    val sharedBlurModifer =
        Modifier
            .clip(sharedButtonSize.shape)
            .hazeChild(hazeState) {
                blurRadius = 10.dp
                backgroundColor = sharedColor
                tints = listOf(HazeTint(sharedColor))
                fallbackTint = HazeTint(sharedColor)
            }

    val bottomState =
        updateTransition(
            targetState =
            when {
                generationStatus.value == SKUGenerationUIStatus.LOADING -> {
                    ImageSelectorState.GENERATION_LOADING
                }

                else -> ImageSelectorState.LAST_IMAGE_SAVED
            },
            label = "bottomState",
        )

    bottomState.AnimatedContent(
        modifier = modifier,
        transitionSpec = { transitionAnimation },
    ) { state ->
        when (state) {
            ImageSelectorState.LAST_IMAGE_SAVED -> {
                FashionButton(
                    modifier = when (imageSelectorFeature.uploadsHistory?.buttons?.mode) {
                        ButtonsMode.BLURRED -> sharedModifier.then(sharedBlurModifer)
                        else -> sharedModifier
                    },
                    text = imageSelectorFeature.strings.imageSelectorButtonChangePhoto,
                    style = when (imageSelectorFeature.uploadsHistory?.buttons?.mode) {
                        ButtonsMode.BLURRED -> FashionButtonStyles.primaryStyle(theme)
                        else -> FashionButtonStyles.secondaryStyle(
                            backgroundColor = Color.Transparent,
                            contentColor = theme.colors.primary,
                            borderColor = Color.Transparent,
                        )
                    },
                    size = sharedButtonSize,
                    onClick = {
                        if (countGeneratedOperation.value == 0) {
                            uploadPhoto()
                        } else {
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

                val solvedText = solveLoadingGenerationText()
                val textTransition = updateTransition(solvedText.value)

                Row(
                    modifier =
                    finalModifier
                        .then(sharedBlurModifer)
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

                    AiutaIcon(
                        modifier =
                        Modifier
                            .size(14.dp)
                            .rotate(angle.value),
                        icon = theme.icons.loading14,
                        tint = theme.colors.primary,
                        contentDescription = null,
                    )

                    Spacer(Modifier.width(8.dp))

                    textTransition.AnimatedContent(
                        transitionSpec = { transitionAnimation },
                    ) { text ->
                        Text(
                            text = text,
                            style = theme.typography.smallButton,
                            color = theme.colors.primary,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}

package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.body.blocks

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.molecules.images.AiutaIcon
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.icon.AiutaIcon
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.isCustomLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features.FeedbackFeatureUiModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features.toTranslatedString
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.SessionImageUIModel
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDataController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.data.provideFeedbackFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic.sendLikeGenerationFeedback
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.GenerationResultController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.showThanksFeedbackBlock
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeChild

@Composable
internal fun FeedbackBlock(
    modifier: Modifier = Modifier,
    sessionImage: SessionImageUIModel,
    itemIndex: Int,
    hazeState: HazeState,
    generationResultController: GenerationResultController,
    isInterfaceVisible: State<Boolean>,
) {
    val controller = LocalController.current
    val dataController = LocalAiutaTryOnDataController.current
    val stringResources = LocalAiutaTryOnStringResources.current

    val feedbackData =
        remember {
            mutableStateOf<FeedbackFeatureUiModel?>(null)
        }
    val isFeedbackVisible =
        remember(sessionImage, feedbackData, stringResources) {
            derivedStateOf {
                val isFeedbackNotProvided = !sessionImage.isFeedbackProvided
                val backendCondition = feedbackData.value != null
                val customLanguageCondition =
                    stringResources.isCustomLanguage() &&
                        stringResources.feedbackSheetTitle != null

                isFeedbackNotProvided &&
                    isInterfaceVisible.value &&
                    (backendCondition || customLanguageCondition)
            }
        }

    val onFeedbackClick = {
        val title: String?
        val options: List<String>
        val extraOption: String?
        val extraOptionTitle: String?

        // In priority custom language
        if (stringResources.isCustomLanguage()) {
            title = stringResources.feedbackSheetTitle
            options = stringResources.feedbackSheetOptions.orEmpty()
            extraOption = stringResources.feedbackSheetExtraOption
            extraOptionTitle = stringResources.feedbackSheetExtraOptionTitle
        } else {
            val data = feedbackData.value

            title = data?.title?.toTranslatedString(stringResources)
            options =
                data?.mainOptions?.mapNotNull {
                    it.toTranslatedString(
                        stringResources,
                    )
                }.orEmpty()
            extraOption = data?.plaintextOption?.toTranslatedString(stringResources)
            extraOptionTitle = data?.plaintextTitle?.toTranslatedString(stringResources)
        }

        if (title != null) {
            controller.bottomSheetNavigator.show(
                newSheetScreen =
                    NavigationBottomSheetScreen.Feedback(
                        title = title,
                        itemIndex = itemIndex,
                        options = options,
                        extraOption = extraOption,
                        extraOptionTitle = extraOptionTitle,
                    ),
            )
        } else {
            generationResultController.showThanksFeedbackBlock()
        }
    }

    LaunchedEffect(Unit) {
        // Don't call it, because in custom language you should provide feedback
        if (!stringResources.isCustomLanguage()) {
            feedbackData.value = dataController.provideFeedbackFeature()
        }
    }

    AnimatedVisibility(
        modifier = modifier,
        visible = isFeedbackVisible.value,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        FeedbackBlockContent(
            hazeState = hazeState,
            onDislikeClick = {
                onFeedbackClick()
                controller.sessionGenerationInteractor.setFeedbackAsProvided(sessionImage)
            },
            onLikeClick = {
                controller.sendLikeGenerationFeedback()
                generationResultController.showThanksFeedbackBlock()
                controller.sessionGenerationInteractor.setFeedbackAsProvided(sessionImage)
            },
        )
    }
}

@Composable
private fun FeedbackBlockContent(
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    onDislikeClick: () -> Unit,
    onLikeClick: () -> Unit,
) {
    val theme = LocalTheme.current

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ReactionIcon(
            icon = theme.icons.like36,
            hazeState = hazeState,
            onClick = {
                onLikeClick()
            },
        )

        Spacer(Modifier.width(8.dp))

        ReactionIcon(
            icon = theme.icons.dislike36,
            hazeState = hazeState,
            onClick = {
                onDislikeClick()
            },
        )
    }
}

@Composable
private fun ReactionIcon(
    modifier: Modifier = Modifier,
    icon: AiutaIcon,
    hazeState: HazeState,
    onClick: () -> Unit,
) {
    val haptic = LocalHapticFeedback.current
    val theme = LocalTheme.current

    val sizeModifier = modifier.size(38.dp)
    val finalModifier =
        if (theme.toggles.isBlurOutlinesEnabled) {
            sizeModifier
                .border(
                    width = 1.dp,
                    color = theme.colors.neutral2,
                    shape = CircleShape,
                )
        } else {
            sizeModifier
        }

    Box(
        modifier =
            finalModifier
                .clip(CircleShape)
                .hazeChild(hazeState) {
                    val sharedColor = theme.colors.background.copy(alpha = 0.4f)

                    blurRadius = 10.dp
                    backgroundColor = sharedColor
                    tints = listOf(HazeTint(sharedColor))
                    fallbackTint = HazeTint(sharedColor)
                },
        contentAlignment = Alignment.Center,
    ) {
        AiutaIcon(
            modifier =
                Modifier
                    .size(24.dp)
                    .clickableUnindicated {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onClick()
                    },
            icon = icon,
            contentDescription = null,
            tint = theme.colors.background.copy(alpha = 0.7f),
        )
    }
}

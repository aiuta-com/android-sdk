package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.FashionIcon
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features.FeedbackFeatureUiModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features.toTranslatedString
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDataController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.data.provideFeedbackFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen

@Composable
internal fun FeedbackBlock(
    modifier: Modifier = Modifier,
    isSwipeTipVisible: Boolean,
) {
    val controller = LocalController.current
    val dataController = LocalAiutaTryOnDataController.current
    val stringResources = LocalAiutaTryOnStringResources.current

    val isFeedbackSelected =
        remember {
            mutableStateOf(false)
        }
    val feedbackData =
        remember {
            mutableStateOf<FeedbackFeatureUiModel?>(null)
        }
    val isFeedbackVisible =
        remember {
            derivedStateOf {
                !isFeedbackSelected.value && feedbackData.value != null && !isSwipeTipVisible
            }
        }

    val onFeedbackClick = {
        val data = feedbackData.value
        val feedbackSheetTitle = data?.title?.toTranslatedString(stringResources)

        if (feedbackSheetTitle != null) {
            controller.bottomSheetNavigator.show(
                newSheetScreen =
                    NavigationBottomSheetScreen.Feedback(
                        title = feedbackSheetTitle,
                        options =
                            data.mainOptions.mapNotNull {
                                it.toTranslatedString(
                                    stringResources,
                                )
                            },
                        extraOption = data.plaintextOption?.toTranslatedString(stringResources),
                        extraOptionTitle = data.plaintextTitle?.toTranslatedString(stringResources),
                    ),
            )
        } else {
            // TODO just show block with thanks
        }
    }

    LaunchedEffect(Unit) {
        feedbackData.value = dataController.provideFeedbackFeature()
    }

    AnimatedVisibility(
        modifier = modifier,
        visible = isFeedbackVisible.value,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        FeedbackBlockContent(
            onDislikeClick = {
                // TODO
//                isFeedbackSelected.value = true
            },
            onLikeClick = {
                // TODO
                onFeedbackClick()
//                isFeedbackSelected.value = true
            },
        )
    }
}

@Composable
private fun FeedbackBlockContent(
    modifier: Modifier = Modifier,
    onDislikeClick: () -> Unit,
    onLikeClick: () -> Unit,
) {
    val haptic = LocalHapticFeedback.current

    Row(
        modifier =
            modifier
                .background(
                    color = Color.Black.copy(alpha = 0.12f),
                    shape = RoundedCornerShape(20.dp),
                )
                .padding(
                    horizontal = 16.dp,
                    vertical = 10.dp,
                ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier =
                Modifier
                    .size(22.dp)
                    .clickableUnindicated {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onDislikeClick()
                    },
            imageVector = ImageVector.vectorResource(FashionIcon.Dislike16),
            contentDescription = null,
            tint = Color.White.copy(alpha = 0.7f),
        )

        Spacer(Modifier.width(26.dp))

        Icon(
            modifier =
                Modifier
                    .size(22.dp)
                    .clickableUnindicated {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onLikeClick()
                    },
            imageVector = ImageVector.vectorResource(FashionIcon.Like16),
            contentDescription = null,
            tint = Color.White.copy(alpha = 0.7f),
        )
    }
}

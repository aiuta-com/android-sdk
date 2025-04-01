package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.molecules.images.AiutaImage
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.CustomLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features.FeedbackFeatureUiModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features.toTranslatedString
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDataController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.data.provideFeedbackFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.GenerationResultController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.showThanksFeedbackBlock

@Composable
internal fun ThanksFeedbackBlock(
    modifier: Modifier = Modifier,
    generationResultController: GenerationResultController,
) {
    val controller = LocalController.current
    val dataController = LocalAiutaTryOnDataController.current
    val stringResources = LocalAiutaTryOnStringResources.current

    val bottomSheetNavigator = controller.bottomSheetNavigator
    val feedbackData =
        remember {
            mutableStateOf<FeedbackFeatureUiModel?>(null)
        }
    val gratitudeMessage =
        (stringResources as? CustomLanguage)?.feedbackSheetGratitude
            ?: feedbackData.value?.gratitudeMessage?.toTranslatedString()

    LaunchedEffect(Unit) {
        feedbackData.value = dataController.provideFeedbackFeature()
    }

    LaunchedEffect(bottomSheetNavigator.lastBottomSheetScreen.value) {
        val lastBottomSheetScreen = bottomSheetNavigator.lastBottomSheetScreen.value

        if (
            lastBottomSheetScreen is NavigationBottomSheetScreen.Feedback ||
            lastBottomSheetScreen is NavigationBottomSheetScreen.ExtraFeedback
        ) {
            generationResultController.showThanksFeedbackBlock()
            bottomSheetNavigator.lastBottomSheetScreen.value = NavigationBottomSheetScreen.IDLE
        }
    }

    AnimatedVisibility(
        modifier = modifier,
        visible = generationResultController.isThanksFeedbackBlockVisible.value && gratitudeMessage != null,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        gratitudeMessage?.let {
            ThanksFeedbackBlockContent(
                gratitudeMessage = gratitudeMessage,
            )
        }
    }
}

@Composable
private fun ThanksFeedbackBlockContent(
    modifier: Modifier = Modifier,
    gratitudeMessage: String,
) {
    val theme = LocalTheme.current

    Column(
        modifier =
        modifier
            .width(168.dp)
            .background(
                color = Color.Black.copy(alpha = 0.8f),
                shape = RoundedCornerShape(24.dp),
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(45.dp))

        AiutaImage(
            modifier = Modifier.size(40.dp),
            image = theme.images.feedbackThanksImage,
            contentDescription = null,
            contentScale = ContentScale.Fit,
        )

        Spacer(Modifier.height(24.dp))

        Text(
            modifier = Modifier.padding(horizontal = 24.dp),
            text = gratitudeMessage,
            style = theme.typography.regular,
            color = theme.colors.onDark,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(20.dp))
    }
}

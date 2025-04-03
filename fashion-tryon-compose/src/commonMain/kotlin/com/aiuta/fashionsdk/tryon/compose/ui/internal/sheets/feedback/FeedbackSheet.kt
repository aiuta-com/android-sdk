package com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.feedback

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.molecules.button.FashionButton
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonSizes
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonStyles
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.feedback.AiutaTryOnFeedbackFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.feedback.other.AiutaTryOnFeedbackOtherFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic.sendGenerationFeedback
import com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.components.SheetDivider
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.isFeatureInitialize
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.strictProvideFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.transitionAnimation

@Composable
internal fun ColumnScope.FeedbackSheet() {
    val controller = LocalController.current
    val theme = LocalTheme.current

    val feedbackFeature = strictProvideFeature<AiutaTryOnFeedbackFeature>()
    val feedbackOptions = feedbackFeature.strings.tryOnFeedbackOptions

    val sharedModifier =
        Modifier
            .padding(horizontal = 16.dp)
            .align(Alignment.CenterHorizontally)

    val selectedOption =
        remember {
            mutableStateOf<String?>(null)
        }
    val isSendButtonVisible =
        remember {
            derivedStateOf {
                selectedOption.value != null
            }
        }

    SheetDivider()

    Spacer(Modifier.height(30.dp))

    Text(
        modifier = sharedModifier,
        text = feedbackFeature.strings.tryOnFeedbackTitle,
        style = theme.typography.titleM,
        color = theme.colors.primary,
        textAlign = TextAlign.Center,
    )

    Spacer(Modifier.height(32.dp))

    feedbackOptions.forEachIndexed { index, option ->
        key(option) {
            OptionItem(
                modifier = sharedModifier,
                option = option,
                isSelected = option == selectedOption.value,
                onClick = {
                    selectedOption.value = option
                },
            )

            if (index != feedbackOptions.lastIndex) {
                Spacer(Modifier.height(12.dp))
            }
        }
    }

    if (isFeatureInitialize<AiutaTryOnFeedbackOtherFeature>()) {
        val feedbackOtherFeature = strictProvideFeature<AiutaTryOnFeedbackOtherFeature>()

        Spacer(Modifier.height(12.dp))

        OptionItem(
            modifier = sharedModifier,
            option = feedbackOtherFeature.strings.otherFeedbackOptionOther,
            isSelected = false,
            onClick = {
                controller.bottomSheetNavigator.change(
                    newSheetScreen =
                    NavigationBottomSheetScreen.ExtraFeedback(
                        optionIndex = feedbackOptions.size,
                    ),
                )
            },
        )
    }

    Spacer(Modifier.height(40.dp))

    AnimatedContent(
        modifier = sharedModifier.height(50.dp),
        targetState = isSendButtonVisible.value,
        transitionSpec = { transitionAnimation },
    ) { isVisibleButton ->
        if (!isVisibleButton) {
            Text(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .clickableUnindicated {
                        controller.sendGenerationFeedback(
                            optionIndex = feedbackOptions.indexOf(selectedOption.value),
                        )
                        controller.bottomSheetNavigator.hide()
                    },
                text = feedbackFeature.strings.tryOnFeedbackButtonSkip,
                style = theme.typography.chips,
                color = theme.colors.secondary,
                textAlign = TextAlign.Center,
            )
        } else {
            FashionButton(
                modifier = Modifier.fillMaxWidth(),
                text = feedbackFeature.strings.tryOnFeedbackButtonSend,
                style = FashionButtonStyles.primaryStyle(theme),
                size = FashionButtonSizes.lSize(),
                onClick = {
                    controller.sendGenerationFeedback(
                        optionIndex = feedbackOptions.indexOf(selectedOption.value),
                        feedback = selectedOption.value,
                    )
                    controller.bottomSheetNavigator.hide()
                },
            )
        }
    }

    Spacer(Modifier.height(16.dp))
}

@Composable
private fun OptionItem(
    modifier: Modifier = Modifier,
    option: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val theme = LocalTheme.current

    Box(
        modifier =
        modifier
            .border(
                width = 2.dp,
                color = if (isSelected) {
                    theme.colors.primary
                } else {
                    Color.Transparent
                },
                shape = theme.shapes.buttonM,
            )
            .background(
                color = if (isSelected) {
                    theme.colors.background
                } else {
                    theme.colors.neutral
                },
                shape = theme.shapes.buttonM,
            )
            .clickableUnindicated { onClick() }
            .padding(
                horizontal = 12.dp,
                vertical = 10.dp,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = option,
            style = theme.typography.chips,
            color = if (isSelected) {
                theme.colors.primary
            } else {
                theme.colors.secondary
            },
            textAlign = TextAlign.Center,
        )
    }
}

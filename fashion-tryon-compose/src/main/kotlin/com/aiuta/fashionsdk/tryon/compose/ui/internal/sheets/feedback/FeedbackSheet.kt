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
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic.sendGenerationFeedback
import com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.components.SheetDivider
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.transitionAnimation

@Composable
internal fun ColumnScope.FeedbackSheet(feedbackData: NavigationBottomSheetScreen.Feedback) {
    val controller = LocalController.current
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

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

    SheetDivider(topPadding = 18.dp)

    Spacer(Modifier.height(30.dp))

    Text(
        modifier = sharedModifier,
        text = feedbackData.title,
        style = theme.typography.titleL,
        color = theme.colors.primary,
        textAlign = TextAlign.Center,
    )

    Spacer(Modifier.height(32.dp))

    feedbackData.options.forEachIndexed { index, option ->
        key(option) {
            OptionItem(
                modifier = sharedModifier,
                option = option,
                isSelected = option == selectedOption.value,
                onClick = {
                    selectedOption.value = option.takeIf { selectedOption.value == null }
                },
            )

            if (index != feedbackData.options.lastIndex) {
                Spacer(Modifier.height(12.dp))
            }
        }
    }

    if (feedbackData.isExtraVisible) {
        feedbackData.extraOption?.let {
            Spacer(Modifier.height(12.dp))

            OptionItem(
                modifier = sharedModifier,
                option = it,
                isSelected = false,
                onClick = {
                    feedbackData.extraOptionTitle?.let { extraOptionTitle ->
                        controller.bottomSheetNavigator.change(
                            newSheetScreen =
                                NavigationBottomSheetScreen.ExtraFeedback(
                                    extraOptionTitle = extraOptionTitle,
                                    itemIndex = feedbackData.itemIndex,
                                ),
                        )
                    }
                },
            )
        }
    }

    Spacer(Modifier.height(40.dp))

    AnimatedContent(
        modifier = sharedModifier,
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
                                generationIndex = feedbackData.itemIndex,
                            )
                            controller.bottomSheetNavigator.hide()
                        },
                text = stringResources.feedbackSheetSkip,
                style = theme.typography.chips,
                color = theme.colors.secondary,
                textAlign = TextAlign.Center,
            )
        } else {
            FashionButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResources.feedbackSheetSend,
                style = FashionButtonStyles.primaryStyle(theme),
                icon = theme.icons.magic16,
                size = FashionButtonSizes.lSize(),
                onClick = {
                    controller.sendGenerationFeedback(
                        generationIndex = feedbackData.itemIndex,
                        feedback = selectedOption.value,
                    )
                    controller.bottomSheetNavigator.hide()
                },
            )
        }
    }

    Spacer(Modifier.height(24.dp))
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
                    color =
                        if (isSelected) {
                            theme.colors.primary
                        } else {
                            Color.Transparent
                        },
                    shape = theme.shapes.buttonM,
                )
                .background(
                    color =
                        if (isSelected) {
                            theme.colors.background
                        } else {
                            theme.colors.neutral
                        },
                    shape = theme.shapes.buttonM,
                )
                .clickableUnindicated { onClick() }
                .padding(
                    horizontal = 16.dp,
                    vertical = 10.dp,
                ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = option,
            style = theme.typography.chips,
            color =
                if (isSelected) {
                    theme.colors.primary
                } else {
                    theme.colors.secondary
                },
            textAlign = TextAlign.Center,
        )
    }
}

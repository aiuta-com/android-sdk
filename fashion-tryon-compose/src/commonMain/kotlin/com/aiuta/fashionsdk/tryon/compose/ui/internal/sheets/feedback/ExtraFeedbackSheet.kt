package com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.feedback

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import com.aiuta.fashionsdk.compose.molecules.images.AiutaIcon
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic.sendGenerationFeedback

@Composable
internal fun ColumnScope.ExtraFeedbackSheet(data: NavigationBottomSheetScreen.ExtraFeedback) {
    val controller = LocalController.current
    val stringResources = LocalAiutaTryOnStringResources.current
    val theme = LocalTheme.current

    val feedbackText = remember { mutableStateOf("") }

    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars)
            .windowInsetsPadding(WindowInsets.ime),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(14.dp))

        AiutaIcon(
            modifier =
            Modifier
                .size(24.dp)
                .align(Alignment.End)
                .clickableUnindicated {
                    controller.bottomSheetNavigator.hide()
                },
            icon = theme.icons.close24,
            contentDescription = null,
            tint = theme.colors.primary,
        )

        Spacer(Modifier.height(32.dp))

        Text(
            modifier = Modifier.align(Alignment.Start),
            text = data.extraOptionTitle,
            style = theme.typography.titleL,
            color = theme.colors.primary,
            textAlign = TextAlign.Start,
        )

        Spacer(Modifier.height(20.dp))

        TextField(
            modifier =
            Modifier
                .fillMaxWidth()
                .heightIn(min = 160.dp)
                .animateContentSize(),
            value = feedbackText.value,
            textStyle = theme.typography.regular,
            onValueChange = { feedbackText.value = it },
            shape = RoundedCornerShape(16.dp),
            colors =
            TextFieldDefaults.textFieldColors(
                textColor = theme.colors.primary,
                backgroundColor = theme.colors.neutral,
                cursorColor = theme.colors.primary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
        )

        Spacer(Modifier.weight(1f))

        FashionButton(
            modifier = Modifier.fillMaxWidth().windowInsetsPadding(WindowInsets.ime),
            text = stringResources.feedbackSheetSendFeedback,
            style = FashionButtonStyles.primaryStyle(theme),
            size = FashionButtonSizes.lSize(),
            onClick = {
                controller.sendGenerationFeedback(
                    optionIndex = data.optionIndex,
                    feedback = feedbackText.value,
                )
                controller.bottomSheetNavigator.hide()
            },
        )

        Spacer(Modifier.height(8.dp))
    }
}

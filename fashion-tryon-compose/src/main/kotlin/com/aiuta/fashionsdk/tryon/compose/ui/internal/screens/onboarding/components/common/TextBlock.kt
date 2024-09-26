package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.components.common

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.OnboardingController

@Composable
internal fun ColumnScope.TextBlock(
    modifier: Modifier = Modifier,
    onboardingController: OnboardingController,
) {
    val theme = LocalTheme.current

    Text(
        modifier = modifier,
        text = onboardingController.state.value.topic,
        style = theme.typography.titleL,
        color = theme.colors.primary,
        textAlign = TextAlign.Start,
    )

    Spacer(Modifier.height(18.dp))

    Text(
        modifier = modifier,
        text = onboardingController.state.value.subtopic,
        style = theme.typography.regular,
        color = theme.colors.primary,
        textAlign = TextAlign.Start,
    )
}

package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.components.consent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalRippleConfiguration
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.OnboardingController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.updateAgreementState

@Composable
internal fun ConsentPageContent(
    modifier: Modifier = Modifier,
    onboardingController: OnboardingController,
) {
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    Column(
        modifier = modifier.padding(horizontal = 24.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResources.onboardingPageConsentTopic,
            style = theme.typography.titleL,
            color = theme.colors.primary,
            textAlign = TextAlign.Start,
        )

        Spacer(Modifier.height(18.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResources.onboardingPageConsentBody,
            style = theme.typography.regular,
            color = theme.colors.primary,
            textAlign = TextAlign.Start,
        )

        Spacer(Modifier.height(56.dp))

        AgreePoint(
            modifier = Modifier.fillMaxWidth(),
            onboardingController = onboardingController,
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun AgreePoint(
    modifier: Modifier = Modifier,
    onboardingController: OnboardingController,
) {
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top,
    ) {
        CompositionLocalProvider(LocalRippleConfiguration provides null) {
            Checkbox(
                modifier = Modifier.size(20.dp),
                checked = onboardingController.isAgreementChecked.value,
                onCheckedChange = { onboardingController.updateAgreementState(it) },
                colors =
                    CheckboxDefaults.colors(
                        checkedColor = theme.colors.brand,
                        uncheckedColor = theme.colors.neutral,
                        checkmarkColor = theme.colors.onDark,
                    ),
            )
        }

        Spacer(Modifier.width(16.dp))

        Text(
            modifier = Modifier.weight(1f),
            text = stringResources.onboardingPageConsentAgreePoint,
            style = theme.typography.regular,
            color = theme.colors.primary,
            textAlign = TextAlign.Start,
        )
    }
}

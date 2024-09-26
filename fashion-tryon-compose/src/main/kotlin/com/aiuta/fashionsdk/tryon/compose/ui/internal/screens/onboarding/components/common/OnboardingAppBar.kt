package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.components.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter
import com.aiuta.fashionsdk.compose.tokens.icon.back24
import com.aiuta.fashionsdk.compose.tokens.icon.close24
import com.aiuta.fashionsdk.internal.analytic.model.FinishSession
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.clickClose
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.appbar.AppBar
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.appbar.AppBarIcon
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.OnboardingController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.previousPage

@Composable
internal fun OnboardingAppBar(
    modifier: Modifier = Modifier,
    onboardingController: OnboardingController,
) {
    val controller = LocalController.current
    val configuration = LocalAiutaConfiguration.current
    val theme = LocalTheme.current

    AppBar(
        modifier = modifier,
        navigationIcon = {
            AppBarIcon(
                modifier = Modifier.align(Alignment.CenterStart),
                painter = rememberAsyncImagePainter(theme.icons.back24),
                color = theme.colors.primary,
                onClick = {
                    onboardingController.previousPage(controller)
                },
            )
        },
        title = {
            if (configuration.isOnboardingAppBarExtended) {
                // TODO
            }
        },
        actions = {
            if (configuration.isPreOnboardingAvailable) {
                AppBarIcon(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    painter = rememberAsyncImagePainter(theme.icons.close24),
                    color = theme.colors.primary,
                    onClick = {
                        controller.clickClose(FinishSession.Origin.ONBOARDING_SCREEN)
                    },
                )
            }
        },
    )
}

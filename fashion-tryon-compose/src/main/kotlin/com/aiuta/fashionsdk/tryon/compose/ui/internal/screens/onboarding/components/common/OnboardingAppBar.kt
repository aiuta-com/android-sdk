package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.components.common

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import coil.compose.rememberAsyncImagePainter
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.internal.analytic.model.FinishSession
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.clickClose
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.appbar.AppBar
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.appbar.AppBarIcon
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.OnboardingController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.previousPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.BestResultPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.ConsentPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.TryOnPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.buildAnnotatedStringFromHtml
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.transitionAnimation

@Composable
internal fun OnboardingAppBar(
    modifier: Modifier = Modifier,
    onboardingController: OnboardingController,
) {
    val controller = LocalController.current
    val configuration = LocalAiutaConfiguration.current
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    val titleTransition = updateTransition(onboardingController.state.value)

    AppBar(
        modifier = modifier,
        navigationIcon = {
            AppBarIcon(
                modifier = Modifier.align(Alignment.CenterStart),
                painter = rememberAsyncImagePainter(theme.icons.back24.resource),
                color = theme.colors.primary,
                onClick = {
                    onboardingController.previousPage(controller)
                },
            )
        },
        title = {
            if (configuration.isOnboardingAppBarExtended) {
                titleTransition.AnimatedContent(
                    transitionSpec = { transitionAnimation },
                ) { state ->
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text =
                            buildAnnotatedStringFromHtml(
                                input =
                                    when (state) {
                                        TryOnPage -> stringResources.onboardingAppbarTryonPage
                                        BestResultPage -> stringResources.onboardingAppbarBestResultPage
                                        ConsentPage -> stringResources.onboardingAppbarConsentPage
                                    },
                                isClickable = false,
                            ),
                        style = theme.typography.navbar,
                        color = theme.colors.primary,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        },
        actions = {
            if (configuration.isOnboardingAppBarExtended) {
                AppBarIcon(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    painter = rememberAsyncImagePainter(theme.icons.close24.resource),
                    color = theme.colors.primary,
                    onClick = {
                        controller.clickClose(FinishSession.Origin.ONBOARDING_SCREEN)
                    },
                )
            }
        },
    )
}

package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.components.common

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.style.TextAlign
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.clickClose
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
    val theme = LocalTheme.current

    val titleTransition = updateTransition(onboardingController.state.value)

    AppBar(
        modifier = modifier,
        navigationIcon = {
            AppBarIcon(
                modifier = Modifier.align(Alignment.CenterStart),
                icon = theme.icons.back24,
                color = theme.colors.primary,
                onClick = {
                    onboardingController.previousPage(controller)
                },
            )
        },
        title = {
            if (theme.toggles.isOnboardingAppBarExtended) {
                titleTransition.AnimatedContent(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    transitionSpec = { transitionAnimation },
                ) { state ->
                    state.pageTitle?.let { pageTitle ->
                        Text(
                            modifier =
                            Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center),
                            text =
                            buildAnnotatedStringFromHtml(
                                input = pageTitle,
                                isClickable = false,
                            ),
                            style =
                            theme.typography.navbar.copy(
                                fontSynthesis = FontSynthesis.All,
                            ),
                            color = theme.colors.primary,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        },
        actions = {
            if (theme.toggles.isOnboardingAppBarExtended) {
                AppBarIcon(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    icon = theme.icons.close24,
                    color = theme.colors.primary,
                    onClick = {
                        controller.clickClose(
                            pageId =
                            when (onboardingController.state.value) {
                                is TryOnPage -> AiutaAnalyticPageId.HOW_IT_WORKS
                                is BestResultPage -> AiutaAnalyticPageId.BEST_RESULTS
                                is ConsentPage -> AiutaAnalyticPageId.CONSENT
                            },
                        )
                    },
                )
            }
        },
    )
}

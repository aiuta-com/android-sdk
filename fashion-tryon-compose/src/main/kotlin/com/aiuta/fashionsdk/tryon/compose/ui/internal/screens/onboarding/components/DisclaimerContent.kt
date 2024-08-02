package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.OnboardingController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.utils.openUri

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun DisclaimerContent(
    modifier: Modifier = Modifier,
    onboardingController: OnboardingController,
) {
    val context = LocalContext.current
    val configuration = LocalAiutaConfiguration.current
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    val urlTag = "LEGAL_DISCLAIMER_TAG"
    val isVisible =
        remember {
            derivedStateOf {
                onboardingController.pagerState.settledPage == onboardingController.pagerState.pageCount - 1 &&
                    configuration.legalDisclaimerUrl != null
            }
        }

    val isVisibleTransition = updateTransition(targetState = isVisible.value)

    val disclaimerString =
        buildAnnotatedString {
            append(stringResources.onboardingLegalDisclaimerNonClickable)

            append("\n")

            pushStringAnnotation(tag = urlTag, annotation = "${configuration.legalDisclaimerUrl}")
            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                append(stringResources.onboardingLegalDisclaimerClickable)
            }
            pop()
        }

    isVisibleTransition.AnimatedContent(
        modifier = modifier,
        transitionSpec = { fadeIn() togetherWith fadeOut() },
    ) { visible ->
        if (visible) {
            ClickableText(
                modifier = Modifier.fillMaxWidth(),
                text = disclaimerString,
                style =
                    MaterialTheme.typography.caption.copy(
                        color = theme.colors.secondary,
                        textAlign = TextAlign.Center,
                        lineHeight = (14.32).sp,
                    ),
                onClick = { offset ->
                    disclaimerString
                        .getStringAnnotations(tag = urlTag, start = offset, end = offset)
                        .firstOrNull()
                        ?.let {
                            context.openUri(it.item)
                        }
                },
            )
        } else {
            Box(Modifier.fillMaxSize())
        }
    }
}

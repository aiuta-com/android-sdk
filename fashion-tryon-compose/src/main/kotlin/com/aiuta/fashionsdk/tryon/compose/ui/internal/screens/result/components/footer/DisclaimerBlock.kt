package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features.FitDisclaimerFeatureUiModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features.toTranslatedString
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDataController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.data.provideFitDisclaimerFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen

@Composable
internal fun BoxWithConstraintsScope.DisclaimerBlock(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val dataController = LocalAiutaTryOnDataController.current

    val disclaimerData =
        remember {
            mutableStateOf<FitDisclaimerFeatureUiModel?>(null)
        }

    LaunchedEffect(Unit) {
        disclaimerData.value = dataController.provideFitDisclaimerFeature()
    }

    AnimatedVisibility(
        modifier = modifier,
        visible = disclaimerData.value != null,
        enter = slideInVertically(),
        exit = slideOutVertically(),
    ) {
        val data = disclaimerData.value
        val disclaimerText = data?.text?.toTranslatedString()

        data?.title?.toTranslatedString()?.let { title ->
            DisclaimerBlockContent(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .clickableUnindicated(disclaimerText != null) {
                            disclaimerText?.let {
                                controller.bottomSheetNavigator.show(
                                    newSheetScreen =
                                        NavigationBottomSheetScreen.FitDisclaimer(
                                            text = disclaimerText,
                                        ),
                                )
                            }
                        },
                title = title,
            )
        }
    }
}

@Composable
private fun DisclaimerBlockContent(
    modifier: Modifier = Modifier,
    title: String,
) {
    val theme = LocalTheme.current

    Box(
        modifier =
            modifier
                .background(
                    color = theme.colors.neutral,
                    shape = theme.shapes.bottomSheet,
                )
                .padding(top = 6.dp),
        contentAlignment = Alignment.TopCenter,
    ) {
        Text(
            text = title,
            style = theme.typography.description,
            color = theme.colors.primary,
            textAlign = TextAlign.Center,
        )
    }
}

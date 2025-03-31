package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.molecules.images.AiutaIcon
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen

@Composable
internal fun DisclaimerBlock(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val stringResources = LocalAiutaTryOnStringResources.current

    DisclaimerBlockContent(
        modifier =
            modifier
                .clickableUnindicated {
                    controller.bottomSheetNavigator.show(
                        newSheetScreen =
                            NavigationBottomSheetScreen.FitDisclaimer(
                                text = stringResources.fitDisclaimerBody,
                            ),
                    )
                },
        title = stringResources.fitDisclaimerTitle,
    )
}

@Composable
private fun DisclaimerBlockContent(
    modifier: Modifier = Modifier,
    title: String,
) {
    val theme = LocalTheme.current

    Row(
        modifier =
            modifier
                .background(
                    color = theme.colors.neutral,
                    shape = theme.shapes.bottomSheet,
                ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = title,
            style = theme.typography.description,
            color = theme.colors.primary,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.width(4.dp))

        AiutaIcon(
            modifier = Modifier.size(20.dp),
            icon = theme.icons.info20,
            contentDescription = null,
            tint = theme.colors.primary,
        )
    }
}

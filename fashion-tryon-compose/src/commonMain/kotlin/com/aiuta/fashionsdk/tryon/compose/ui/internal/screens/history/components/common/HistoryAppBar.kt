package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.components.common

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.activateSelectMode
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.isAppbarSelectAvailable
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateBack
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.appbar.AppBar
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.appbar.AppBarIcon

@Composable
internal fun HistoryAppBar(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    val isAppbarSelectAvailable = controller.isAppbarSelectAvailable()

    val actionColorCalculation: (Boolean) -> Color = { active ->
        if (active) {
            theme.colors.primary
        } else {
            theme.colors.secondary
        }
    }

    val selectColorTransition =
        animateColorAsState(
            targetValue = actionColorCalculation(isAppbarSelectAvailable.value),
            label = "selectColorTransition",
        )

    AppBar(
        modifier = modifier,
        navigationIcon = {
            AppBarIcon(
                modifier = Modifier.align(Alignment.CenterStart),
                icon = theme.icons.back24,
                color = theme.colors.primary,
                onClick = controller::navigateBack,
            )
        },
        title = {
            Text(
                modifier = Modifier.fillMaxWidth().align(Alignment.Center),
                text = stringResources.appBarHistory,
                style = theme.typography.navbar,
                color = theme.colors.primary,
                textAlign = TextAlign.Center,
            )
        },
        actions = {
            Text(
                modifier =
                Modifier
                    .align(Alignment.CenterEnd)
                    .clickableUnindicated { if (isAppbarSelectAvailable.value) controller.activateSelectMode() },
                text = stringResources.appBarSelect,
                style = theme.typography.navbar,
                color = selectColorTransition.value,
            )
        },
    )
}

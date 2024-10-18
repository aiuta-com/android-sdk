package com.aiuta.fashionsdk.tryon.compose.ui.internal.components.appbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.FinishSession
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.clickClose
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.isAppbarHistoryAvailable
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateTo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.appbar.AppBar
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.appbar.AppBarIcon

@Composable
internal fun MainAppBar(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    val isAppbarHistoryAvailable = controller.isAppbarHistoryAvailable()

    val historyButton: @Composable BoxScope.(modifier: Modifier) -> Unit = { innerModifier ->
        AnimatedVisibility(
            modifier = innerModifier,
            visible = isAppbarHistoryAvailable.value,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            AppBarIcon(
                icon = theme.icons.history24,
                color = theme.colors.primary,
                onClick = {
                    controller.navigateTo(NavigationScreen.HISTORY)
                },
            )
        }
    }

    val closeButton: @Composable BoxScope.(modifier: Modifier) -> Unit = { innerModifier ->
        AppBarIcon(
            modifier = innerModifier,
            icon = theme.icons.close24,
            color = theme.colors.primary,
            onClick = {
                controller.clickClose(
                    FinishSession.Origin.MAIN_SCREEN,
                    AiutaAnalyticPageId.IMAGE_PICKER,
                )
            },
        )
    }

    AppBar(
        modifier = modifier,
        navigationIcon = {
            val navigationIconModifier = Modifier.align(Alignment.CenterStart)

            if (theme.toggles.isMainAppbarReversed) {
                historyButton(navigationIconModifier)
            } else {
                closeButton(navigationIconModifier)
            }
        },
        title = {
            Text(
                modifier = Modifier.fillMaxWidth().align(Alignment.Center),
                text = stringResources.virtualTryOn,
                style = theme.typography.navbar,
                color = theme.colors.primary,
                textAlign = TextAlign.Center,
            )
        },
        actions = {
            val actionModifier = Modifier.align(Alignment.CenterEnd)

            if (theme.toggles.isMainAppbarReversed) {
                closeButton(actionModifier)
            } else {
                historyButton(actionModifier)
            }
        },
    )
}

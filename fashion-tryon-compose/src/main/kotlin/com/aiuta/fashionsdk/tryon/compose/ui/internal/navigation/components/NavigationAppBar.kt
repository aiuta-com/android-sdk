package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.FashionIcon
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.R
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.activateSelectMode
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.appbarState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.isAppbarHistoryAvailable
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.isAppbarSelectAvailable
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationAppBarState

private val slideEnterAnimation = fadeIn() + expandVertically()
private val slideExitAnimation = fadeOut() + shrinkVertically()
private val slideTransitionAnimation = slideEnterAnimation togetherWith slideExitAnimation

@Composable
internal fun NavigationAppBar(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateToHistory: () -> Unit,
) {
    val controller = LocalController.current
    val theme = LocalTheme.current
    val appbarState = controller.appbarState()
    val isAppbarHistoryAvailable = controller.isAppbarHistoryAvailable()
    val isAppbarSelectAvailable = controller.isAppbarSelectAvailable()

    val appbarTransition =
        updateTransition(
            targetState = appbarState.value,
            label = "appbarTransition",
        )

    val actionColorCalculation: (Boolean) -> Color = { active ->
        if (active) {
            theme.colors.primary
        } else {
            theme.colors.primary.copy(alpha = 0.4f)
        }
    }

    val historyColorTransition =
        animateColorAsState(
            targetValue = actionColorCalculation(isAppbarHistoryAvailable.value),
            label = "historyColorTransition",
        )

    val selectColorTransition =
        animateColorAsState(
            targetValue = actionColorCalculation(isAppbarSelectAvailable.value),
            label = "selectColorTransition",
        )

    TopAppBar(
        modifier = modifier,
        backgroundColor = theme.colors.background,
        elevation = 0.dp,
        navigationIcon = {
            Icon(
                modifier =
                    Modifier
                        .size(36.dp)
                        .clickableUnindicated {
                            navigateBack()
                        },
                imageVector = ImageVector.vectorResource(FashionIcon.Arrow36),
                contentDescription = null,
                tint = theme.colors.primary,
            )
        },
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                appbarTransition.AnimatedContent(
                    transitionSpec = { slideTransitionAnimation },
                ) { state ->
                    when (state) {
                        NavigationAppBarState.GENERAL -> {
                            Icon(
                                modifier = Modifier.padding(bottom = 4.dp),
                                painter = painterResource(theme.navLogo),
                                tint = theme.colors.navLogoColor,
                                contentDescription = null,
                            )
                        }

                        NavigationAppBarState.HISTORY -> {
                            Text(
                                modifier = Modifier.padding(bottom = 4.dp),
                                text = stringResource(R.string.app_bar_history),
                                style = MaterialTheme.typography.h6,
                                color = theme.colors.primary,
                            )
                        }

                        NavigationAppBarState.EMPTY -> Unit
                    }
                }
            }
        },
        actions = {
            appbarTransition.AnimatedContent(
                transitionSpec = { slideTransitionAnimation },
            ) { state ->
                when (state) {
                    NavigationAppBarState.GENERAL -> {
                        Text(
                            modifier =
                                Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(end = 8.dp)
                                    .clickableUnindicated {
                                        if (isAppbarHistoryAvailable.value) {
                                            navigateToHistory()
                                        }
                                    },
                            text = stringResource(R.string.app_bar_history),
                            style = MaterialTheme.typography.h6,
                            color = historyColorTransition.value,
                        )
                    }

                    NavigationAppBarState.HISTORY -> {
                        Text(
                            modifier =
                                Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(end = 8.dp)
                                    .clickableUnindicated {
                                        if (isAppbarSelectAvailable.value) {
                                            controller.activateSelectMode()
                                        }
                                    },
                            text = stringResource(R.string.app_bar_select),
                            style = MaterialTheme.typography.h6,
                            color = selectColorTransition.value,
                        )
                    }

                    NavigationAppBarState.EMPTY -> Unit
                }
            }
        },
    )
}

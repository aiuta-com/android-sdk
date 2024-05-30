package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.FashionIcon
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.activateSelectMode
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.appbarActionState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.appbarState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.isAppbarHistoryAvailable
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.isAppbarSelectAvailable
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationAppBarActionState
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
    val stringResources = LocalAiutaTryOnStringResources.current
    val appbarState = controller.appbarState()
    val appbarActionState = controller.appbarActionState()
    val isAppbarHistoryAvailable = controller.isAppbarHistoryAvailable()
    val isAppbarSelectAvailable = controller.isAppbarSelectAvailable()

    val actionColor = theme.navBarTheme.foregroundColor ?: theme.colors.primary

    val appbarTransition =
        updateTransition(
            targetState = appbarState.value,
            label = "appbarTransition",
        )

    val appbarActionTransition =
        updateTransition(
            targetState = appbarActionState.value,
            label = "appbarActionTransition",
        )

    val actionColorCalculation: (Boolean) -> Color = { active ->
        if (active) {
            actionColor
        } else {
            actionColor.copy(alpha = 0.4f)
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

    AppBar(
        modifier = modifier,
        navigationIcon = {
            Icon(
                modifier =
                    Modifier
                        .align(Alignment.CenterStart)
                        .size(36.dp)
                        .clickableUnindicated {
                            navigateBack()
                        },
                imageVector = ImageVector.vectorResource(FashionIcon.Arrow36),
                contentDescription = null,
                tint = actionColor,
            )
        },
        title = {
            appbarTransition.AnimatedContent(
                modifier = Modifier.align(Alignment.Center),
                transitionSpec = { slideTransitionAnimation },
            ) { state ->
                when (state) {
                    NavigationAppBarState.GENERAL -> {
                        Image(
                            painter = painterResource(theme.navBarTheme.navLogo),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                        )
                    }

                    NavigationAppBarState.HISTORY -> {
                        Text(
                            text = stringResources.appBarHistory,
                            style = MaterialTheme.typography.h6,
                            color = theme.colors.primary,
                        )
                    }

                    NavigationAppBarState.EMPTY -> Unit
                }
            }
        },
        actions = {
            appbarActionTransition.AnimatedContent(
                modifier = Modifier.align(Alignment.CenterEnd),
                transitionSpec = { slideTransitionAnimation },
            ) { state ->
                when (state) {
                    NavigationAppBarActionState.HISTORY -> {
                        AppBarActionText(
                            text = stringResources.appBarHistory,
                            enable = isAppbarHistoryAvailable,
                            colorTransition = historyColorTransition,
                            onClick = navigateToHistory,
                        )
                    }

                    NavigationAppBarActionState.SELECT_PHOTOS -> {
                        AppBarActionText(
                            text = stringResources.appBarSelect,
                            enable = isAppbarSelectAvailable,
                            colorTransition = selectColorTransition,
                            onClick = controller::activateSelectMode,
                        )
                    }

                    NavigationAppBarActionState.EMPTY -> Unit
                }
            }
        },
    )
}

@Composable
private fun AppBar(
    modifier: Modifier = Modifier,
    navigationIcon: @Composable BoxScope.() -> Unit,
    title: @Composable BoxScope.() -> Unit,
    actions: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier,
    ) {
        navigationIcon()

        title()

        actions()
    }
}

@Composable
private fun AppBarActionText(
    modifier: Modifier = Modifier,
    text: String,
    enable: State<Boolean>,
    colorTransition: State<Color>,
    onClick: () -> Unit,
) {
    Text(
        modifier =
            modifier
                .padding(end = 8.dp)
                .clickableUnindicated {
                    if (enable.value) onClick()
                },
        text = text,
        style = MaterialTheme.typography.h6,
        color = colorTransition.value,
    )
}

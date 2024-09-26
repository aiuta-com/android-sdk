package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.appbar

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
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.aiuta.fashionsdk.compose.tokens.icon.back24
import com.aiuta.fashionsdk.compose.tokens.icon.close24
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.activateSelectMode
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.appbarActionState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.appbarNavigationState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.appbarState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.isAppbarHistoryAvailable
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.isAppbarSelectAvailable
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationAppBarActionState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationAppBarNavigationState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationAppBarState

private val slideEnterAnimation = fadeIn() + expandVertically()
private val slideExitAnimation = fadeOut() + shrinkVertically()
private val slideTransitionAnimation = slideEnterAnimation togetherWith slideExitAnimation

@Deprecated("Let's use separate app bar")
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
    val appbarNavigationState = controller.appbarNavigationState()
    val appbarActionState = controller.appbarActionState()

    val isAppbarHistoryAvailable = controller.isAppbarHistoryAvailable()
    val isAppbarSelectAvailable = controller.isAppbarSelectAvailable()

    val actionColor = theme.colors.primary

    val appbarTransition =
        updateTransition(
            targetState = appbarState.value,
            label = "appbarTransition",
        )

    val appbarNavigationTransition =
        updateTransition(
            targetState = appbarNavigationState.value,
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
        modifier = modifier.padding(horizontal = 16.dp),
        navigationIcon = {
            appbarNavigationTransition.AnimatedContent(
                modifier = Modifier.align(Alignment.CenterStart),
                transitionSpec = { slideTransitionAnimation },
            ) { state ->
                when (state) {
                    NavigationAppBarNavigationState.BACK -> {
                        AppBarIcon(
                            modifier = Modifier.align(Alignment.CenterStart),
                            painter = rememberAsyncImagePainter(theme.icons.back24),
                            color = actionColor,
                            onClick = { navigateBack() },
                        )
                    }

                    NavigationAppBarNavigationState.EMPTY -> Unit
                }
            }
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

                    NavigationAppBarActionState.CLOSE -> {
                        AppBarIcon(
                            modifier = Modifier.align(Alignment.CenterStart),
                            painter = rememberAsyncImagePainter(theme.icons.close24),
                            color = actionColor,
                            onClick = {
                                // TODO
                            },
                        )
                    }

                    NavigationAppBarActionState.EMPTY -> Unit
                }
            }
        },
    )
}

@Composable
internal fun AppBar(
    modifier: Modifier = Modifier,
    navigationIcon: @Composable BoxScope.() -> Unit = {},
    title: @Composable BoxScope.() -> Unit = {},
    actions: @Composable BoxScope.() -> Unit = {},
) {
    Box(
        modifier =
            modifier
                .padding(top = 8.dp)
                .windowInsetsPadding(WindowInsets.statusBars),
    ) {
        navigationIcon()

        title()

        actions()
    }
}

@Composable
internal fun AppBarActionText(
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

@Composable
internal fun AppBarIcon(
    modifier: Modifier = Modifier,
    painter: Painter,
    color: Color,
    onClick: () -> Unit,
) {
    Icon(
        modifier =
            modifier
                .size(24.dp)
                .clickableUnindicated { onClick() },
        painter = painter,
        tint = color,
        contentDescription = null,
    )
}

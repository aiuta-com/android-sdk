package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDialogController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.dialog.isDialogVisible
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.hideErrorState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.isErrorStateVisible
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.DEFAULT_SHOWING_DELAY
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.NavigationAlertDialog
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.NavigationContent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.NavigationErrorCard
import kotlinx.coroutines.delay

@Composable
internal fun NavigationContainer(modifier: Modifier = Modifier) {
    val currentController = LocalController.current
    val theme = LocalTheme.current

    ModalBottomSheetLayout(
        modifier = modifier,
        sheetState = currentController.bottomSheetNavigator.sheetState,
        sheetBackgroundColor = theme.colors.background,
        sheetContent = currentController.bottomSheetNavigator.sheetContent,
        sheetContentColor = theme.colors.primary,
        sheetShape = theme.shapes.bottomSheet,
        scrimColor = theme.colors.primary.copy(alpha = 0.6f),
        content = {
            NavigationContainerContent(
                modifier = Modifier.fillMaxSize(),
            )
        },
    )
}

@Composable
private fun NavigationContainerContent(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val dialogController = LocalAiutaTryOnDialogController.current
    val theme = LocalTheme.current

    Box(
        modifier = modifier.background(theme.colors.background),
    ) {
        NavigationContent(modifier = Modifier.fillMaxSize())

        AnimatedVisibility(
            modifier =
                Modifier
                    .align(Alignment.BottomCenter)
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .fillMaxWidth(),
            visible = controller.isErrorStateVisible().value,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            controller.fashionTryOnErrorState.value?.let { state ->

                LaunchedEffect(Unit) {
                    delay(DEFAULT_SHOWING_DELAY)

                    controller.hideErrorState()
                }

                NavigationErrorCard(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                    errorState = state,
                )
            }
        }

        AnimatedVisibility(
            modifier = Modifier.fillMaxSize(),
            visible = dialogController.isDialogVisible().value,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            dialogController.state.value?.let { state ->
                NavigationAlertDialog(state = state)
            }
        }
    }
}

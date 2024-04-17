package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.hideErrorState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.isErrorStateVisible
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateBack
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateTo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen.SKUInfo.PrimaryButtonState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.DEFAULT_SHOWING_DELAY
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.NavigationAppBar
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.NavigationContent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.NavigationErrorCard
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.NavigationSKUItem
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun NavigationContainer(modifier: Modifier = Modifier) {
    val currentController = LocalController.current
    val theme = LocalTheme.current

    ModalBottomSheetLayout(
        modifier = modifier,
        sheetState = currentController.bottomSheetNavigator.sheetState,
        sheetBackgroundColor = theme.colors.backgroundElevation2,
        sheetContent = currentController.bottomSheetNavigator.sheetContent,
        sheetContentColor = theme.colors.primary,
        sheetShape =
            RoundedCornerShape(
                topStart = 24.dp,
                topEnd = 24.dp,
            ),
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
    val theme = LocalTheme.current
    val sharedModifier = Modifier.zIndex(controller.zIndexInterface).fillMaxWidth()

    Box(
        modifier = modifier.background(theme.colors.background),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            NavigationAppBar(
                modifier =
                    sharedModifier.background(
                        theme.colors.background,
                    ).windowInsetsPadding(WindowInsets.statusBars),
                navigateBack = controller::navigateBack,
                navigateToHistory = {
                    controller.navigateTo(NavigationScreen.HISTORY)
                },
            )

            AnimatedVisibility(
                modifier = sharedModifier.background(theme.colors.background),
                visible = controller.isSKUItemVisible.value,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically(),
            ) {
                Column {
                    Divider(
                        modifier = sharedModifier,
                        color = theme.colors.gray2,
                        thickness = 1.dp,
                    )

                    NavigationSKUItem(
                        modifier = Modifier.fillMaxWidth(),
                        onItemClick = {
                            controller.bottomSheetNavigator.show(
                                newSheetScreen =
                                    NavigationBottomSheetScreen.SKUInfo(
                                        primaryButtonState = PrimaryButtonState.ADD_TO_CART,
                                        skuItem = controller.activeSKUItem.value,
                                    ),
                            )
                        },
                    )

                    Divider(
                        modifier = sharedModifier,
                        color = theme.colors.gray2,
                        thickness = 1.dp,
                    )
                }
            }

            NavigationContent(
                modifier = Modifier.weight(1f),
            )
        }

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
    }
}

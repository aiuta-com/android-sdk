package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateBack
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateTo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen.SKUInfo.PrimaryButtonState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.NavigationAppBar
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.NavigationContent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.NavigationSKUItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun NavigationContainer(modifier: Modifier = Modifier) {
    val currentController = LocalController.current
    val theme = LocalTheme.current

    ModalBottomSheetLayout(
        modifier = modifier,
        sheetState = currentController.bottomSheetNavigator.sheetState,
        sheetBackgroundColor = Color.White,
        sheetContent = currentController.bottomSheetNavigator.sheetContent,
        sheetContentColor = theme.colors.background,
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

    Column(
        modifier = modifier.background(theme.colors.background),
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

        Divider(
            modifier = sharedModifier,
            color = theme.colors.gray2,
            thickness = 1.dp,
        )

        AnimatedVisibility(
            modifier = sharedModifier.background(theme.colors.background),
            visible = controller.isSKUItemVisible.value,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
        ) {
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
        }

        Divider(
            modifier = sharedModifier,
            color = theme.colors.gray2,
            thickness = 1.dp,
        )

        NavigationContent(
            modifier = Modifier.weight(1f),
        )
    }
}

package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.picker.ImagePickerSheet
import com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.skuinfo.SKUInfoSheet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun rememberBottomSheetNavigator(): BottomSheetNavigator {
    val defaultScope = rememberCoroutineScope()

    val defaultBottomSheetState =
        rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
        )

    val defaultBottomSheetScreen =
        remember {
            mutableStateOf<NavigationBottomSheetScreen>(NavigationBottomSheetScreen.IDLE)
        }

    return remember {
        BottomSheetNavigator(
            scope = defaultScope,
            sheetState = defaultBottomSheetState,
            currentBottomSheetScreen = defaultBottomSheetScreen,
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Immutable
internal class BottomSheetNavigator(
    private val scope: CoroutineScope,
    internal val sheetState: ModalBottomSheetState,
    public val currentBottomSheetScreen: MutableState<NavigationBottomSheetScreen>,
) {
    public val sheetContent: @Composable ColumnScope.() -> Unit = {
        when (val bottomScreen = currentBottomSheetScreen.value) {
            is NavigationBottomSheetScreen.ImagePicker -> {
                ImagePickerSheet()
            }

            is NavigationBottomSheetScreen.SKUInfo -> {
                SKUInfoSheet(
                    skuInfo = bottomScreen,
                )
            }

            is NavigationBottomSheetScreen.IDLE -> Unit
        }
    }

    public fun show(newSheetScreen: NavigationBottomSheetScreen) {
        scope.launch {
            currentBottomSheetScreen.value = newSheetScreen
            sheetState.show()
        }
    }

    public fun hide() {
        scope.launch {
            sheetState.hide()
            currentBottomSheetScreen.value = NavigationBottomSheetScreen.IDLE
        }
    }
}

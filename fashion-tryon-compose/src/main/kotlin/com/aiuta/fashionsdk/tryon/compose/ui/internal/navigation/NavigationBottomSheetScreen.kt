package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation

import androidx.compose.runtime.Immutable

@Immutable
internal sealed interface NavigationBottomSheetScreen {
    public object IDLE : NavigationBottomSheetScreen

    public object ImagePicker : NavigationBottomSheetScreen

    public class SKUInfo(
        public val primaryButtonState: PrimaryButtonState,
    ) : NavigationBottomSheetScreen {
        public enum class PrimaryButtonState {
            ADD_TO_CART,

            TRY_ON,
        }
    }
}

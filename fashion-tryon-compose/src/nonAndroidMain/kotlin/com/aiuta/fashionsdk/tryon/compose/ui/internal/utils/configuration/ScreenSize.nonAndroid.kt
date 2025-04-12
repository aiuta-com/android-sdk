package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.configuration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal actual fun rememberScreenSize(): ScreenSize {
    val density = LocalDensity.current
    val windowInfo = LocalWindowInfo.current

    return remember(density, windowInfo) {
        ScreenSize(
            heightDp = with(density) { windowInfo.containerSize.height.toDp() },
            widthDp = with(density) { windowInfo.containerSize.width.toDp() },
        )
    }
}

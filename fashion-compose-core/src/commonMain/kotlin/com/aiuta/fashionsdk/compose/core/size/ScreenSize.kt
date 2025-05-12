package com.aiuta.fashionsdk.compose.core.size

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp

@Immutable
public class ScreenSize(
    public val heightDp: Dp,
    public val widthDp: Dp,
)

@Composable
public fun rememberScreenSize(): ScreenSize {
    val density = LocalDensity.current
    val windowInfo = LocalWindowInfo.current

    return remember(density, windowInfo) {
        ScreenSize(
            heightDp = with(density) { windowInfo.containerSize.height.toDp() },
            widthDp = with(density) { windowInfo.containerSize.width.toDp() },
        )
    }
}

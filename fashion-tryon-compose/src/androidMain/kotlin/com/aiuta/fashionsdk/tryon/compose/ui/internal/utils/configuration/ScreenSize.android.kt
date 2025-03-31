package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.configuration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
internal actual fun rememberScreenSize(): ScreenSize {
    val configuration = LocalConfiguration.current

    return remember(configuration) {
        ScreenSize(
            heightDp = configuration.screenHeightDp.dp,
            widthDp = configuration.screenWidthDp.dp,
        )
    }
}

package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.appbar

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

internal val APPBAR_HEIGHT = 52.dp

@Composable
internal fun Modifier.appBarPadding(): Modifier {
    return this.then(
        other =
            Modifier
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(top = APPBAR_HEIGHT),
    )
}

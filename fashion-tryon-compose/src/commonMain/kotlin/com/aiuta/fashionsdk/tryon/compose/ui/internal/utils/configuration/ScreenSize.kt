package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.configuration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp

@Immutable
internal class ScreenSize(
    val heightDp: Dp,
    val widthDp: Dp,
)

@Composable
internal expect fun rememberScreenSize(): ScreenSize

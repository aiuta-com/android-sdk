package com.aiuta.fashionsdk.compose.core.size

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp

@Immutable
public class ScreenSize(
    public val heightDp: Dp,
    public val widthDp: Dp,
)

@Composable
public expect fun rememberScreenSize(): ScreenSize

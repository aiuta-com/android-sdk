package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset

internal fun Offset.toIntOffset() =
    IntOffset(
        x = this.x.toInt(),
        y = this.y.toInt(),
    )

internal fun Float.toDp(density: Density) = with(density) { this@toDp.toDp() }

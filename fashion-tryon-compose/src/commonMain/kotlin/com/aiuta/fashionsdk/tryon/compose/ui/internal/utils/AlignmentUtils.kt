package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.layout.layout

internal val CenterAlignmentLine = HorizontalAlignmentLine { _, new -> new }

internal fun Modifier.createCenterAlignmentLine() = this.then(
    Modifier.layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        val centerPoint = placeable.height / 2
        layout(
            placeable.width,
            placeable.height,
            alignmentLines = mapOf(CenterAlignmentLine to centerPoint),
        ) {
            placeable.placeRelative(0, 0)
        }
    },
)

internal fun Modifier.createCenterAlignmentLine(
    topPosition: Float,
    bottomPosition: Float,
) = this.then(
    Modifier.layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        val centerPoint = topPosition + ((bottomPosition - topPosition) / 2)
        layout(
            placeable.width,
            placeable.height,
            alignmentLines = mapOf(CenterAlignmentLine to centerPoint.toInt()),
        ) {
            placeable.placeRelative(0, 0)
        }
    },
)

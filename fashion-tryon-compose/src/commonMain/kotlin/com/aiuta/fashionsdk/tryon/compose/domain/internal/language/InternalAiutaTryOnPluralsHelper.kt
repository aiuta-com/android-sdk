package com.aiuta.fashionsdk.tryon.compose.domain.internal.language

internal fun Plurals.solveStringResource(count: Int): String {
    val finalString =
        if (count == 1) {
            one
        } else {
            other
        }

    return finalString.replace(
        oldValue = "%d",
        newValue = "$count",
    )
}

package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ScaleFactor

/**
 * Special [ContentScale] to animate transition from [ContentScale.Crop] state
 * to [ContentScale.Fit] depends on current state of [sharedElementProgress]
 */
@Immutable
internal class FitterContentScale(
    public val sharedElementProgress: Animatable<Float, AnimationVector1D>,
) : ContentScale {
    override fun computeScaleFactor(
        srcSize: Size,
        dstSize: Size,
    ): ScaleFactor {
        val cropScale = ContentScale.Crop.computeScaleFactor(srcSize, dstSize)
        val fitScale = ContentScale.Fit.computeScaleFactor(srcSize, dstSize)

        return lerp(
            cropScale,
            fitScale,
            sharedElementProgress.value,
        )
    }

    private fun lerp(
        start: ScaleFactor,
        stop: ScaleFactor,
        fraction: Float,
    ): ScaleFactor {
        return ScaleFactor(
            scaleX = lerp(start.scaleX, stop.scaleX, fraction),
            scaleY = lerp(start.scaleY, stop.scaleY, fraction),
        )
    }

    private fun lerp(
        start: Float,
        stop: Float,
        fraction: Float,
    ): Float {
        return start + (stop - start) * fraction
    }
}

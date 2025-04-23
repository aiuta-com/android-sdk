package com.aiuta.fashionsdk.tryon.compose.domain.internal.share.utls

import kotlin.math.max
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGFloat
import platform.CoreGraphics.CGRect
import platform.CoreGraphics.CGRectGetMaxX
import platform.CoreGraphics.CGRectGetMaxY
import platform.CoreGraphics.CGRectGetMinX
import platform.CoreGraphics.CGRectMake
import platform.CoreGraphics.CGSize
import platform.CoreGraphics.CGSizeMake

// Operators
@OptIn(ExperimentalForeignApi::class)
internal operator fun CValue<CGRect>.times(other: CValue<CGSize>): CValue<CGRect> = useContents originScope@{
    val rectSize = this@originScope.size
    val rectOrigin = this@originScope.origin
    other.useContents otherScope@{
        val otherSize = this@otherScope

        CGRectMake(
            x = rectOrigin.x * otherSize.width,
            y = rectOrigin.y * otherSize.height,
            width = rectSize.width * otherSize.width,
            height = rectSize.height * otherSize.height,
        )
    }
}

@OptIn(ExperimentalForeignApi::class)
internal operator fun CValue<CGSize>.times(scale: Double): CValue<CGSize> = useContents {
    CGSizeMake(
        width = width * scale,
        height = height * scale,
    )
}

// Max/Min
@OptIn(ExperimentalForeignApi::class)
internal val CValue<CGRect>.maxX: CGFloat
    get() = CGRectGetMaxX(this)

@OptIn(ExperimentalForeignApi::class)
internal val CValue<CGRect>.maxY: CGFloat
    get() = CGRectGetMaxY(this)

// Other
@OptIn(ExperimentalForeignApi::class)
internal fun CValue<CGRect>.fit(other: CValue<CGSize>): CValue<CGRect> {
    val minX = CGRectGetMinX(this@fit)
    val minY = CGRectGetMinX(this@fit)

    return useContents originScope@{
        val rectSize = this@originScope.size
        other.useContents otherScope@{
            val otherSize = this@otherScope
            val newSize = rectSize.fit(otherSize)

            newSize.useContents newSizeScope@{
                val newActualSize = this@newSizeScope

                CGRectMake(
                    x = minX + (rectSize.width - newActualSize.width) / 2,
                    y = minY + (rectSize.height - newActualSize.height) / 2,
                    width = newActualSize.width,
                    height = newActualSize.height,
                )
            }
        }
    }
}

@OptIn(ExperimentalForeignApi::class)
internal fun CGSize.fit(other: CGSize): CValue<CGSize> {
    val hScale = other.height / height
    val wScale = other.width / width
    val scale = max(hScale, wScale)

    return CGSizeMake(
        height = other.height / scale,
        width = other.width / scale,
    )
}

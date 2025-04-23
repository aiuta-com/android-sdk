package com.aiuta.fashionsdk.tryon.compose.domain.internal.share.utls

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.aiuta.fashionsdk.tryon.compose.domain.internal.share.utils.toImageBitmap
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.refTo
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGBitmapContextCreate
import platform.CoreGraphics.CGBitmapContextCreateImage
import platform.CoreGraphics.CGBlendMode
import platform.CoreGraphics.CGColorSpaceCreateDeviceRGB
import platform.CoreGraphics.CGContextFillRect
import platform.CoreGraphics.CGContextSetFillColorWithColor
import platform.CoreGraphics.CGImageAlphaInfo
import platform.CoreGraphics.CGRect
import platform.CoreGraphics.CGRectMake
import platform.UIKit.UIColor
import platform.UIKit.UIGraphicsBeginImageContextWithOptions
import platform.UIKit.UIGraphicsEndImageContext
import platform.UIKit.UIGraphicsGetCurrentContext
import platform.UIKit.UIGraphicsGetImageFromCurrentImageContext
import platform.UIKit.UIImage

@OptIn(ExperimentalForeignApi::class)
private val area = CGRectMake(x = 0.5, y = 0.82, width = 0.45, height = 0.14)

@OptIn(ExperimentalForeignApi::class)
internal fun watermarking(
    image: UIImage,
    watermark: Painter?,
    density: Density,
    layoutDirection: LayoutDirection,
): UIImage {
    if (watermark == null) return image

    val watermarkBitmap = watermark.toImageBitmap(
        density = density,
        layoutDirection = layoutDirection,
    )
    val watermarkUIImage = watermarkBitmap.toUIImage() ?: return image

    val areaRect = area * image.size
    val watermarkSize = watermarkUIImage.size * watermarkUIImage.scale
    val drawRect = areaRect.fit(watermarkSize)

    val watermarkRect = drawRect.useContents drawRectScope@{
        val drawRectSize = this@drawRectScope.size

        watermarkSize.useContents watermarkSizeScope@{
            val watermarkActualSize = this@watermarkSizeScope

            val finalDrawSize = if (
                drawRectSize.width > watermarkActualSize.width || drawRectSize.height > watermarkActualSize.height
            ) {
                watermarkActualSize
            } else {
                drawRectSize
            }

            CGRectMake(
                x = areaRect.maxX - finalDrawSize.width,
                y = areaRect.maxY - finalDrawSize.height,
                width = finalDrawSize.width,
                height = finalDrawSize.height,
            )
        }
    }

    return appendWatermark(
        image = image,
        watermark = watermarkUIImage,
        watermarkRect = watermarkRect,
        density = density,
        layoutDirection = layoutDirection,
    )
}

@OptIn(ExperimentalForeignApi::class)
internal fun appendWatermark(
    image: UIImage,
    watermark: UIImage,
    watermarkRect: CValue<CGRect>,
    density: Density,
    layoutDirection: LayoutDirection,
): UIImage {
    val imageRect = image.size.useContents { CGRectMake(0.0, 0.0, width, height) }

    UIGraphicsBeginImageContextWithOptions(image.size, true, 0.0)
    val context = UIGraphicsGetCurrentContext() ?: return image

    CGContextSetFillColorWithColor(context, UIColor.blackColor.CGColor)
    CGContextFillRect(context, imageRect)

    image.drawInRect(imageRect, blendMode = CGBlendMode.kCGBlendModeNormal, alpha = 1.0)
    watermark.drawInRect(watermarkRect, blendMode = CGBlendMode.kCGBlendModeNormal, alpha = 1.0)

    val result = UIGraphicsGetImageFromCurrentImageContext()
    UIGraphicsEndImageContext()

    return result ?: image
}

@OptIn(ExperimentalForeignApi::class)
internal fun ImageBitmap.toUIImage(): UIImage? {
    val width = this.width
    val height = this.height
    val buffer = IntArray(width * height)

    this.readPixels(buffer)

    val colorSpace = CGColorSpaceCreateDeviceRGB()
    val context = CGBitmapContextCreate(
        data = buffer.refTo(0),
        width = width.toULong(),
        height = height.toULong(),
        bitsPerComponent = 8u,
        bytesPerRow = (4 * width).toULong(),
        space = colorSpace,
        bitmapInfo = CGImageAlphaInfo.kCGImageAlphaPremultipliedLast.value,
    )

    val cgImage = CGBitmapContextCreateImage(context)
    return cgImage?.let { UIImage.imageWithCGImage(it) }
}

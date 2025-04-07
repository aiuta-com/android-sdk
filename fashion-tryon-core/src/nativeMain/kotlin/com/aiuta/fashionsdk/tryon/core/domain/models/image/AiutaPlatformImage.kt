package com.aiuta.fashionsdk.tryon.core.domain.models.image

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import com.aiuta.fashionsdk.tryon.core.domain.models.compressor.CompressionConfig
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.get
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.useContents
import org.jetbrains.skia.Image
import platform.CoreGraphics.CGRectMake
import platform.CoreGraphics.CGSizeMake
import platform.Foundation.NSData
import platform.UIKit.UIGraphicsBeginImageContextWithOptions
import platform.UIKit.UIGraphicsEndImageContext
import platform.UIKit.UIGraphicsGetImageFromCurrentImageContext
import platform.UIKit.UIImage
import platform.UIKit.UIImageJPEGRepresentation

@Immutable
public actual class AiutaPlatformImage(
    private val image: UIImage,
) {
    public actual val byteArray: ByteArray by lazy { toByteArray() }

    public actual val imageBitmap: ImageBitmap by lazy { toImageBitmap() }

    @OptIn(ExperimentalForeignApi::class)
    private fun toByteArray(): ByteArray {
        val compressionConfig = CompressionConfig.DEFAULT
        val nativeQuality = compressionConfig.compressedImageQuality / 100.0

        val imageWidth = image.size.useContents { this.width }
        val imageHeight = image.size.useContents { this.height }

        val resizedImage =
            if (
                imageWidth > compressionConfig.compressedImageMaxSize ||
                imageHeight > compressionConfig.compressedImageMaxSize
            ) {
                resizeImage(image, compressionConfig.compressedImageMaxSize.toDouble())
            } else {
                image
            }

        val imageData =
            resizedImage.jpegDataWithQuality(nativeQuality)
                ?: error("Unable to convert image to JPEG")

        val bytes = imageData.bytes ?: error("image bytes is null")
        val length = imageData.length

        val data: CPointer<ByteVar> = bytes.reinterpret()
        return ByteArray(length.toInt()) { index -> data[index] }
    }

    private fun toImageBitmap(): ImageBitmap {
        val byteArray = byteArray
        return Image.makeFromEncoded(byteArray).toComposeImageBitmap()
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun resizeImage(
        image: UIImage,
        maxSize: Double,
    ): UIImage {
        val originalSize = image.size
        val width = originalSize.useContents { this.width }
        val height = originalSize.useContents { this.height }

        val ratio = if (width > height) maxSize / width else maxSize / height
        val newWidth = width * ratio
        val newHeight = height * ratio

        val newSize = CGSizeMake(newWidth, newHeight)
        UIGraphicsBeginImageContextWithOptions(newSize, false, 1.0)
        image.drawInRect(CGRectMake(0.0, 0.0, newWidth, newHeight))
        val resizedImage = UIGraphicsGetImageFromCurrentImageContext()!!
        UIGraphicsEndImageContext()

        return resizedImage
    }

    private fun UIImage.jpegDataWithQuality(quality: Double): NSData? = UIImageJPEGRepresentation(this, quality)
}

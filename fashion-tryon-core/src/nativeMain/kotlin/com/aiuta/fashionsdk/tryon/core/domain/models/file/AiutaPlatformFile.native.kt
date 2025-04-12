package com.aiuta.fashionsdk.tryon.core.domain.models.file

import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.tryon.core.domain.models.compressor.AiutaCompressionConfig
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.get
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.useContents
import kotlinx.cinterop.usePinned
import platform.CoreGraphics.CGRectMake
import platform.CoreGraphics.CGSizeMake
import platform.Foundation.NSData
import platform.Foundation.NSURL
import platform.Foundation.dataWithContentsOfURL
import platform.UIKit.UIGraphicsBeginImageContextWithOptions
import platform.UIKit.UIGraphicsEndImageContext
import platform.UIKit.UIGraphicsGetImageFromCurrentImageContext
import platform.UIKit.UIImage
import platform.UIKit.UIImageJPEGRepresentation
import platform.posix.memcpy

public actual class AiutaPlatformFile(
    public val url: NSURL,
    internal val tempUrl: NSURL,
) {
    public constructor(url: NSURL) : this(url, url)
}

@OptIn(ExperimentalForeignApi::class)
public actual suspend fun AiutaPlatformFile.readByteArray(platformContext: AiutaPlatformContext): ByteArray {
    val data = NSData.dataWithContentsOfURL(tempUrl) ?: return ByteArray(0)
    val byteArraySize: Int = if (data.length > Int.MAX_VALUE.toUInt()) {
        Int.MAX_VALUE
    } else {
        data.length.toInt()
    }

    return ByteArray(byteArraySize).apply {
        usePinned {
            memcpy(it.addressOf(0), data.bytes, data.length)
        }
    }
}

@OptIn(ExperimentalForeignApi::class)
public actual suspend fun AiutaPlatformFile.readCompressedByteArray(
    platformContext: AiutaPlatformContext,
    config: AiutaCompressionConfig,
): ByteArray {
    val nativeQuality = config.compressedImageQuality / 100.0

    val nsData = NSData.dataWithContentsOfURL(tempUrl)
    val image = nsData?.let { UIImage(data = nsData) } ?: error("Failed to solve image")

    val imageWidth = image.size.useContents { this.width }
    val imageHeight = image.size.useContents { this.height }

    val resizedImage = if (
        imageWidth > config.compressedImageMaxSize ||
        imageHeight > config.compressedImageMaxSize
    ) {
        resizeImage(image, config.compressedImageMaxSize.toDouble())
    } else {
        image
    }

    val imageData = resizedImage.jpegDataWithQuality(nativeQuality)
        ?: error("Unable to convert image to JPEG")

    val bytes = imageData.bytes ?: error("image bytes is null")
    val length = imageData.length

    val data: CPointer<ByteVar> = bytes.reinterpret()
    return ByteArray(length.toInt()) { index -> data[index] }
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

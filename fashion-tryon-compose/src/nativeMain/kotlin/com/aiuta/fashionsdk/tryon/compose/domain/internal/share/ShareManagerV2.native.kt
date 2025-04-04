package com.aiuta.fashionsdk.tryon.compose.domain.internal.share

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import coil3.Image
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.request.ImageRequest
import coil3.toBitmap
import com.aiuta.fashionsdk.compose.tokens.images.AiutaImage
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.tryon.compose.domain.internal.share.utils.generateImageFileName
import com.aiuta.fashionsdk.tryon.core.domain.models.image.AiutaPlatformImage
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.refTo
import kotlinx.cinterop.usePinned
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.Image.Companion.makeFromBitmap
import platform.CoreFoundation.CFDataCreate
import platform.CoreGraphics.CGColorRenderingIntent
import platform.CoreGraphics.CGColorSpaceCreateDeviceRGB
import platform.CoreGraphics.CGDataProviderCreateWithCFData
import platform.CoreGraphics.CGImageAlphaInfo
import platform.CoreGraphics.CGImageCreate
import platform.CoreGraphics.kCGBitmapByteOrder32Little
import platform.Foundation.NSData
import platform.Foundation.NSTemporaryDirectory
import platform.Foundation.NSURL
import platform.Foundation.dataWithBytes
import platform.Foundation.writeToFile
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication
import platform.UIKit.UIImage

internal actual class ShareManagerV2 {
    actual suspend fun shareImages(
        content: String?,
        pageId: AiutaAnalyticPageId,
        productId: String?,
        images: List<AiutaPlatformImage>,
        watermark: AiutaImage?,
    ): Result<Unit> {
        // TODO Add watermark
        return runCatching {
            val urls =
                withContext(Dispatchers.IO) {
                    images.mapNotNull { image -> saveFile(image.byteArray) }
                }
            val activityViewController = UIActivityViewController(urls, null)

            UIApplication.sharedApplication.keyWindow?.rootViewController?.presentViewController(
                activityViewController,
                animated = true,
                completion = null,
            )
        }
    }

    actual suspend fun shareImages(
        coilContext: PlatformContext,
        content: String?,
        pageId: AiutaAnalyticPageId,
        productId: String?,
        imageUrls: List<String>,
        watermark: AiutaImage?,
    ): Result<Unit> {
        return runCatching {
            val images =
                imageUrls.mapNotNull { url ->
                    val uiimage = urlToUIImage(coilContext, url)
                    uiimage?.let { AiutaPlatformImage(it) }
                }

            shareImages(
                content = content,
                pageId = pageId,
                productId = productId,
                images = images,
                watermark = watermark,
            )
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun saveFile(bytes: ByteArray): NSURL? {
        val tempDir = NSTemporaryDirectory()
        val sharedFile = tempDir + generateImageFileName()
        val saved =
            bytes.usePinned {
                val nsData = NSData.dataWithBytes(it.addressOf(0), bytes.size.toULong())
                nsData.writeToFile(sharedFile, true)
            }
        return if (saved) NSURL.fileURLWithPath(sharedFile) else null
    }

    private suspend fun urlToUIImage(
        coilContext: PlatformContext,
        imageUrl: String,
    ): UIImage? {
        return try {
            val request =
                ImageRequest.Builder(coilContext)
                    .data(imageUrl)
                    .build()
            val resultImage = SingletonImageLoader.get(coilContext).execute(request).image

            resultImage?.let { convertImage(resultImage) }
        } catch (e: Exception) {
            // Failed to resolve bitmap
            null
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    fun convertImage(image: Image): UIImage? {
        val bitmap: Bitmap = image.toBitmap()
        val skikoImage = makeFromBitmap(bitmap)
        val skikoImagePixelMap = skikoImage.peekPixels()
        if (skikoImagePixelMap != null) {
            val cfDataRef =
                CFDataCreate(
                    allocator = null,
                    bytes = skikoImagePixelMap.buffer.bytes.asUByteArray().refTo(0),
                    length = skikoImagePixelMap.buffer.size.toLong(),
                )
            val cgImageRef =
                CGImageCreate(
                    width = skikoImage.width.toULong(),
                    height = skikoImage.height.toULong(),
                    bitsPerComponent = 8u,
                    bitsPerPixel = 32u,
                    bytesPerRow = (skikoImage.width * 4).toULong(),
                    space = CGColorSpaceCreateDeviceRGB(),
                    bitmapInfo = kCGBitmapByteOrder32Little or CGImageAlphaInfo.kCGImageAlphaPremultipliedFirst.value,
                    provider = CGDataProviderCreateWithCFData(cfDataRef),
                    decode = null,
                    shouldInterpolate = true,
                    intent = CGColorRenderingIntent.kCGRenderingIntentDefault,
                )
            return UIImage(cGImage = cgImageRef)
        }
        return null
    }
}

@Composable
internal actual fun rememberShareManagerV2(): ShareManagerV2 {
    return remember { ShareManagerV2() }
}

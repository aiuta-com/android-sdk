package com.aiuta.fashionsdk.tryon.core.domain.models.image

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import com.aiuta.fashionsdk.tryon.core.domain.models.compressor.CompressionConfig
import java.awt.Graphics2D
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

@Immutable
public actual class AiutaPlatformImage(
    private val bufferedImage: BufferedImage,
) {
    public actual val byteArray: ByteArray by lazy { toByteArray() }

    public actual val imageBitmap: ImageBitmap by lazy { bufferedImage.toComposeImageBitmap() }

    private fun toByteArray(): ByteArray {
        val compressionConfig = CompressionConfig.DEFAULT

        val resizedImage =
            if (bufferedImage.width > compressionConfig.compressedImageMaxSize ||
                bufferedImage.height > compressionConfig.compressedImageMaxSize
            ) {
                resizeImage(bufferedImage, compressionConfig.compressedImageMaxSize)
            } else {
                bufferedImage
            }

        return ByteArrayOutputStream().use { output ->
            ImageIO.write(resizedImage, "jpg", output)
            output.toByteArray()
        }
    }

    private fun resizeImage(image: BufferedImage, maxSize: Int): BufferedImage {
        val inWidth = image.width
        val inHeight = image.height

        val outWidth = if (inWidth > inHeight) maxSize else inWidth * maxSize / inHeight
        val outHeight = if (inWidth > inHeight) inHeight * maxSize / inWidth else maxSize

        val resized = BufferedImage(outWidth, outHeight, BufferedImage.TYPE_INT_RGB)
        val g2d: Graphics2D = resized.createGraphics()
        g2d.drawImage(image.getScaledInstance(outWidth, outHeight, Image.SCALE_SMOOTH), 0, 0, null)
        g2d.dispose()

        return resized
    }
}

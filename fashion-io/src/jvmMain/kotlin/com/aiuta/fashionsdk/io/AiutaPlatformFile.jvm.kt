package com.aiuta.fashionsdk.io

import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.io.compression.AiutaCompressionConfig
import java.awt.Graphics2D
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import javax.imageio.ImageIO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

public actual class AiutaPlatformFile(public val file: File)

public actual suspend fun AiutaPlatformFile.readByteArray(
    platformContext: AiutaPlatformContext,
): ByteArray = file.readBytes()

public actual suspend fun AiutaPlatformFile.readCompressedByteArray(
    platformContext: AiutaPlatformContext,
    config: AiutaCompressionConfig,
): ByteArray {
    val bufferedImage = withContext(Dispatchers.IO) {
        ImageIO.read(file)
    }

    val resizedImage =
        if (bufferedImage.width > config.compressedImageMaxSize ||
            bufferedImage.height > config.compressedImageMaxSize
        ) {
            resizeImage(bufferedImage, config.compressedImageMaxSize)
        } else {
            bufferedImage
        }

    return ByteArrayOutputStream().use { output ->
        ImageIO.write(resizedImage, "jpg", output)
        output.toByteArray()
    }
}

private fun resizeImage(
    image: BufferedImage,
    maxSize: Int,
): BufferedImage {
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

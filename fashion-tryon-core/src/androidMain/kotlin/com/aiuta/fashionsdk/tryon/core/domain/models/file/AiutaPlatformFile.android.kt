package com.aiuta.fashionsdk.tryon.core.domain.models.file

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.exifinterface.media.ExifInterface
import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.tryon.core.domain.models.compressor.AiutaCompressionConfig
import com.aiuta.fashionsdk.tryon.core.domain.models.file.utils.generateFile
import com.aiuta.fashionsdk.tryon.core.domain.models.file.utils.writeByteArray
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

public actual class AiutaPlatformFile(public val uri: Uri)

public actual suspend fun AiutaPlatformFile.readByteArray(
    platformContext: AiutaPlatformContext,
): ByteArray {
    val inputStream = platformContext.contentResolver.openInputStream(uri)
        ?: throw FileNotFoundException("File not found")

    return inputStream.use { stream ->
        stream.readBytes()
    }
}

public actual suspend fun AiutaPlatformFile.readCompressedByteArray(
    platformContext: AiutaPlatformContext,
    config: AiutaCompressionConfig,
): ByteArray {
    return try {
        val compressedImageFile = generateFile(
            path = platformContext.cacheDir.absolutePath,
        ) ?: return readByteArray(platformContext)

        val compressedImageByteArray = resizeImageAndCompress(
            platformContext = platformContext,
            imageUri = uri,
            config = config,
        )

        val isByteArraySuccessfullyWritten = compressedImageFile.writeByteArray(
            byteArray = compressedImageByteArray,
        )

        if (isByteArraySuccessfullyWritten) {
            platformContext.contentResolver.openInputStream(uri)?.use {
                restoreExifInfoAfterCompression(it, compressedImageFile)
            }
        }

        if (isByteArraySuccessfullyWritten) {
            compressedImageFile.readBytes()
        } else {
            readByteArray(platformContext)
        }
    } catch (e: Exception) {
        readByteArray(platformContext)
    }
}

@SuppressLint("UseKtx")
private fun resizeBitmap(
    image: Bitmap,
    maxSize: Int,
): Bitmap {
    val inWidth: Int = image.width
    val inHeight: Int = image.height

    val outWidth = if (inWidth > inHeight) maxSize else inWidth * maxSize / inHeight
    val outHeight = if (inWidth > inHeight) inHeight * maxSize / inWidth else maxSize

    return Bitmap.createScaledBitmap(image, outWidth, outHeight, false)
}

private fun resizeImageAndCompress(
    platformContext: AiutaPlatformContext,
    imageUri: Uri,
    config: AiutaCompressionConfig,
): ByteArray {
    val inputStream = platformContext.contentResolver.openInputStream(imageUri)
    val bitmap = BitmapFactory.decodeStream(BufferedInputStream(inputStream))

    val resizedBitmap =
        if (bitmap.width > config.compressedImageMaxSize ||
            bitmap.height > config.compressedImageMaxSize
        ) {
            resizeBitmap(bitmap, config.compressedImageMaxSize)
        } else {
            bitmap
        }
    val byteArrayOutputStream = ByteArrayOutputStream()

    resizedBitmap.compress(
        Bitmap.CompressFormat.JPEG,
        config.compressedImageQuality,
        byteArrayOutputStream,
    )

    return byteArrayOutputStream.toByteArray()
}

private fun restoreExifInfoAfterCompression(
    imageUriInputStream: InputStream,
    compressedImageFile: File,
) {
    val originalImageExif = ExifInterface(imageUriInputStream)
    val compressedImageExif = ExifInterface(compressedImageFile)

    with(originalImageExif.getAttribute(ExifInterface.TAG_ORIENTATION)) {
        if (this == null) {
            return@with
        }

        compressedImageExif.setAttribute(ExifInterface.TAG_ORIENTATION, this)
        compressedImageExif.saveAttributes()
    }
}

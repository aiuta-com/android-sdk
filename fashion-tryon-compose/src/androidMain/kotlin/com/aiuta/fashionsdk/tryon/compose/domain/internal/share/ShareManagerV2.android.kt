package com.aiuta.fashionsdk.tryon.compose.domain.internal.share

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.request.ImageRequest
import coil3.request.allowHardware
import coil3.toBitmap
import com.aiuta.fashionsdk.compose.tokens.images.AiutaDrawableImage
import com.aiuta.fashionsdk.compose.tokens.images.AiutaImage
import com.aiuta.fashionsdk.compose.tokens.images.AiutaResourceImage
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.tryon.compose.domain.internal.share.utils.addWatermark
import com.aiuta.fashionsdk.tryon.compose.domain.internal.share.utils.getUriFromBitmap
import com.aiuta.fashionsdk.tryon.compose.domain.internal.share.utils.shareContent
import com.aiuta.fashionsdk.tryon.core.domain.models.image.AiutaPlatformImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal actual class ShareManagerV2(
    private val context: Context,
) {
    actual suspend fun shareImages(
        content: String?,
        pageId: AiutaAnalyticPageId,
        productId: String?,
        images: List<AiutaPlatformImage>,
        watermark: AiutaImage?,
    ): Result<Unit> {
        return runCatching {
            val imageUris = images.mapNotNull { bitmapToUri(it.bitmap) }

            context.shareContent(
                content = content,
                pageId = pageId,
                productId = productId,
                fileUris = imageUris,
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
                    val bitmap = urlToBitmap(coilContext, url)
                    bitmap?.let { AiutaPlatformImage(it) }
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

    private suspend fun bitmapToUri(
        bitmap: Bitmap,
        watermark: AiutaImage? = null,
    ): Uri? {
        val bitmapConfig = bitmap.config ?: return null
        return try {
            withContext(Dispatchers.Default) {
                val mutableBitmap = bitmap.copy(bitmapConfig, true)
                val modifierBitmap =
                    try {
                        val proceedWatermark =
                            when (watermark) {
                                is AiutaResourceImage ->
                                    TODO(
                                        "Need to implement work with jetbrains resources",
                                    )

                                is AiutaDrawableImage -> watermark.resource

                                else -> null
                            }

                        proceedWatermark?.let {
                            context.addWatermark(
                                source = mutableBitmap,
                                watermark = proceedWatermark,
                            )
                        } ?: mutableBitmap
                    } catch (e: Exception) {
                        // Failed to add watermark
                        mutableBitmap
                    }

                context.getUriFromBitmap(bmp = modifierBitmap, isCache = true)
            }
        } catch (e: Exception) {
            // Failed get bitmap
            null
        }
    }

    private suspend fun urlToBitmap(
        coilContext: PlatformContext,
        imageUrl: String,
    ): Bitmap? {
        return try {
            val request =
                ImageRequest.Builder(coilContext)
                    .data(imageUrl)
                    .allowHardware(false)
                    .build()
            SingletonImageLoader.get(coilContext).execute(request).image?.toBitmap()
        } catch (e: Exception) {
            // Failed to resolve bitmap
            null
        }
    }
}

@Composable
internal actual fun rememberShareManagerV2(): ShareManagerV2 {
    val context = LocalContext.current

    return remember { ShareManagerV2(context) }
}

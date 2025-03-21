package com.aiuta.fashionsdk.tryon.compose.domain.internal.share

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.toBitmapOrNull
import coil.Coil
import coil.request.ImageRequest
import com.aiuta.fashionsdk.compose.tokens.images.AiutaDrawableImage
import com.aiuta.fashionsdk.compose.tokens.images.AiutaImage
import com.aiuta.fashionsdk.compose.tokens.images.AiutaResourceImage
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.tryon.compose.domain.internal.share.utils.addWatermark
import com.aiuta.fashionsdk.tryon.compose.domain.internal.share.utils.getUriFromBitmap
import com.aiuta.fashionsdk.tryon.compose.domain.internal.share.utils.shareContent
import com.aiuta.fashionsdk.tryon.compose.domain.internal.share.utils.solveDrawableFromWatermark
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class ShareManager(
    private val context: Context,
) {
    private val stateFlow = MutableStateFlow<SharingState>(SharingState.Loading)
    private val workerDispatcher = Dispatchers.Default
    private var innerScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    public fun share(
        content: String? = null,
        pageId: AiutaAnalyticPageId,
        productId: String?,
        imageUrls: List<String>,
        watermark: AiutaImage? = null,
    ): StateFlow<SharingState> {
        return share(
            content = content,
            imageUrls = imageUrls,
            pageId = pageId,
            productId = productId,
            watermark =
                watermark?.let {
                    when (watermark) {
                        is AiutaDrawableImage -> watermark.resource

                        is AiutaResourceImage -> TODO("Support KMP resource")

                        //                            context.solveDrawableFromWatermark(
//                                watermark.resource,
//                            )
                        else -> TODO("Support KMP resource")
                    }
                },
        )
    }

    public fun share(
        content: String? = null,
        pageId: AiutaAnalyticPageId,
        productId: String?,
        imageUrls: List<String>,
        @DrawableRes watermarkRes: Int? = null,
    ): StateFlow<SharingState> {
        return share(
            content = content,
            imageUrls = imageUrls,
            pageId = pageId,
            productId = productId,
            watermark = context.solveDrawableFromWatermark(watermarkRes),
        )
    }

    public fun share(
        content: String? = null,
        pageId: AiutaAnalyticPageId,
        productId: String?,
        imageUrls: List<String>,
        watermark: Drawable? = null,
    ): StateFlow<SharingState> {
        innerScope.launch {
            try {
                val imageUris = ArrayList<Uri>()
                imageUrls
                    .map { image ->
                        async {
                            getUri(
                                imageUrl = image,
                                watermark = watermark,
                            )
                        }
                    }
                    .awaitAll()
                    .filterNotNull()
                    .toCollection(imageUris)

                if (imageUris.isNotEmpty()) {
                    context.shareContent(
                        content = content,
                        pageId = pageId,
                        productId = productId,
                        contentUris = imageUris,
                    )
                    stateFlow.value = SharingState.Success
                } else {
                    stateFlow.value = SharingState.Error("There is no images")
                }
            } catch (ignore: CancellationException) {
                stateFlow.value = SharingState.Idle
            } catch (e: Exception) {
                stateFlow.value = SharingState.Error("Sharing Error")
            }
        }

        return stateFlow
    }

    private suspend fun getUri(
        imageUrl: String,
        watermark: Drawable? = null,
    ): Uri? {
        val bitmap = getBitmap(imageUrl) ?: return null
        val bitmapConfig = bitmap.config ?: return null

        return try {
            withContext(workerDispatcher) {
                val mutableBitmap = bitmap.copy(bitmapConfig, true)
                val modifierBitmap =
                    try {
                        watermark?.let {
                            context.addWatermark(
                                source = mutableBitmap,
                                watermark = watermark,
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

    private suspend fun getBitmap(imageUrl: String): Bitmap? {
        return try {
            val request =
                ImageRequest.Builder(context)
                    .placeholderMemoryCacheKey(this.hashCode().toString())
                    .data(imageUrl)
                    .allowHardware(false)
                    .build()
            Coil.imageLoader(context).execute(request).drawable?.toBitmapOrNull()
        } catch (e: Exception) {
            // Failed to resolve bitmap
            null
        }
    }
}

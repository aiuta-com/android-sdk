package com.aiuta.fashionsdk.tryon.compose.domain.internal.share

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.graphics.drawable.toBitmapOrNull
import coil.Coil
import coil.request.ImageRequest
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
        imageUrls: List<String>,
    ): StateFlow<SharingState> {
        innerScope.launch {
            try {
                val imageUris = ArrayList<Uri>()
                imageUrls
                    .map { image -> async { getUri(image) } }
                    .awaitAll()
                    .filterNotNull()
                    .toCollection(imageUris)
                context.shareContent(content, imageUris)
                stateFlow.value = SharingState.Success
            } catch (ignore: CancellationException) {
                stateFlow.value = SharingState.Idle
            } catch (e: Exception) {
                stateFlow.value = SharingState.Error("Sharing Error")
            }
        }

        return stateFlow
    }

    private suspend fun getUri(imageUrl: String): Uri? {
        val bitmap = getBitmap(imageUrl) ?: return null
        return withContext(workerDispatcher) {
            val modifierBitmap = bitmap.copy(bitmap.config, true)
            context.getUriFromBitmap(bmp = modifierBitmap, isCache = true)
        }
    }

    private suspend fun getBitmap(imageUrl: String): Bitmap? {
        val request =
            ImageRequest.Builder(context)
                .placeholderMemoryCacheKey(this.hashCode().toString())
                .data(imageUrl)
                .allowHardware(false)
                .build()
        return Coil.imageLoader(context).execute(request).drawable?.toBitmapOrNull()
    }
}

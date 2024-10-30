package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.warmup

import android.content.Context
import coil.imageLoader
import coil.request.ImageRequest

internal class WarmUpInteractor(
    val context: Context,
) {
    suspend fun warmUp(url: String) {
        val request =
            ImageRequest.Builder(context)
                .data(url)
                .build()

        context.imageLoader.execute(request)
    }
}

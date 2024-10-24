package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.session

import android.content.Context
import coil.imageLoader
import coil.request.ImageRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

internal class SessionGenerationInteractor(
    val context: Context,
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _sessionGenerationsUrls: ArrayDeque<String> = ArrayDeque()
    val sessionGenerationsUrls: List<String> = _sessionGenerationsUrls

    suspend fun addGeneration(url: String) {
        warmUp(url)
        _sessionGenerationsUrls.addFirst(url)
    }

    suspend fun addGenerations(urls: List<String>) {
        urls.forEach { url -> addGeneration(url) }
    }

    suspend fun warmUp(url: String) {
        val request =
            ImageRequest.Builder(context)
                .data(url)
                .build()

        context.imageLoader.execute(request)
    }
}

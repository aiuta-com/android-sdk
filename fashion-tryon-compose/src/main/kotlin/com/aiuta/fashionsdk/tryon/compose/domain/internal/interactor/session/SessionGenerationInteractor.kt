package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.session

import android.content.Context
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.warmup.WarmUpInteractor

internal class SessionGenerationInteractor(
    private val warmUpInteractor: WarmUpInteractor,
) {
    private val _sessionGenerationsUrls: ArrayDeque<String> = ArrayDeque()
    val sessionGenerationsUrls: List<String> = _sessionGenerationsUrls

    fun deleteGenerations(urls: List<String>) {
        _sessionGenerationsUrls.removeAll(urls)
    }

    suspend fun addGeneration(url: String) {
        warmUpInteractor.warmUp(url)
        _sessionGenerationsUrls.addFirst(url)
    }

    suspend fun addGenerations(urls: List<String>) {
        urls.forEach { url -> addGeneration(url) }
    }

    companion object {
        fun getInstance(context: Context): SessionGenerationInteractor {
            return SessionGenerationInteractor(
                warmUpInteractor = WarmUpInteractor(context),
            )
        }
    }
}

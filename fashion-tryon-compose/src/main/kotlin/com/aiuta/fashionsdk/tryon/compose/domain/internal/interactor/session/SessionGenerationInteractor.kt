package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.session

import android.content.Context
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.warmup.WarmUpInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImageUIModel

internal class SessionGenerationInteractor(
    private val warmUpInteractor: WarmUpInteractor,
) {
    private val _sessionGenerationsUrls: ArrayDeque<GeneratedImageUIModel> = ArrayDeque()
    val sessionGenerationsUrls: List<GeneratedImageUIModel> = _sessionGenerationsUrls

    fun deleteGenerations(urls: List<GeneratedImageUIModel>) {
        _sessionGenerationsUrls.removeAll(urls)
    }

    suspend fun addGeneration(image: GeneratedImageUIModel) {
        warmUpInteractor.warmUp(image.imageUrl)
        _sessionGenerationsUrls.addFirst(image)
    }

    suspend fun addGenerations(images: List<GeneratedImageUIModel>) {
        images.forEach { image -> addGeneration(image) }
    }

    companion object {
        fun getInstance(context: Context): SessionGenerationInteractor {
            return SessionGenerationInteractor(
                warmUpInteractor = WarmUpInteractor(context),
            )
        }
    }
}

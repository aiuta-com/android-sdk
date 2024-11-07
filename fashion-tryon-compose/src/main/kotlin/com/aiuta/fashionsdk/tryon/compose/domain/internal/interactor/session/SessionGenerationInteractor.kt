package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.session

import android.content.Context
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.warmup.WarmUpInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImageUIModel

internal class SessionGenerationInteractor(
    private val warmUpInteractor: WarmUpInteractor,
) {
    private val _sessionGenerations: ArrayDeque<GeneratedImageUIModel> = ArrayDeque()
    val sessionGenerations: List<GeneratedImageUIModel> = _sessionGenerations

    fun deleteGenerations(images: List<GeneratedImageUIModel>) {
        // Check by id, because urls can be different
        val imagesIds = images.map { it.id }.toSet()
        _sessionGenerations.removeAll { it.id in imagesIds }
    }

    fun deleteGeneration(image: GeneratedImageUIModel) {
        _sessionGenerations.removeAll { it.id == image.id }
    }

    suspend fun addGeneration(image: GeneratedImageUIModel) {
        warmUpInteractor.warmUp(image.imageUrl)
        _sessionGenerations.addFirst(image)
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

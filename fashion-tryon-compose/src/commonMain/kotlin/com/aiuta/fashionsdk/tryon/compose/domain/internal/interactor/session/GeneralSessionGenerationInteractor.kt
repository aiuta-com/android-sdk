package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.session

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import coil3.PlatformContext
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.warmup.WarmUpInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImageUIModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.SessionImageUIModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.toSessionUiModel
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized

internal class GeneralSessionGenerationInteractor(
    private val warmUpInteractor: WarmUpInteractor,
) : SynchronizedObject(),
    SessionGenerationInteractor {
    private val _sessionGenerations: SnapshotStateList<SessionImageUIModel> = mutableStateListOf()
    override val sessionGenerations: List<SessionImageUIModel> = _sessionGenerations

    override fun deleteGenerations(images: List<GeneratedImageUIModel>) {
        synchronized(this) {
            // Check by id, because urls can be different
            val imagesIds = images.map { it.id }.toSet()
            _sessionGenerations.removeAll { it.id in imagesIds }
        }
    }

    override fun deleteGeneration(image: SessionImageUIModel) {
        synchronized(this) {
            // For handling ConcurrentModificationException
            val copyImage = image.copy()

            _sessionGenerations.removeAll { copyImage.id == image.id }
        }
    }

    override fun setFeedbackAsProvided(image: SessionImageUIModel) {
        synchronized(this) {
            val imageIndex = _sessionGenerations.indexOf(image)

            if (imageIndex >= 0) {
                _sessionGenerations[imageIndex] = image.copy(isFeedbackProvided = true)
            }
        }
    }

    override suspend fun addGeneration(image: GeneratedImageUIModel) {
        warmUpInteractor.warmUp(image.imageUrl)

        synchronized(this) {
            _sessionGenerations.add(0, image.toSessionUiModel())
        }
    }

    override suspend fun addGenerations(images: List<GeneratedImageUIModel>) {
        images.forEach { image -> addGeneration(image) }
    }

    companion object {
        fun getInstance(coilContext: PlatformContext): GeneralSessionGenerationInteractor = GeneralSessionGenerationInteractor(
            warmUpInteractor = WarmUpInteractor(coilContext),
        )
    }
}

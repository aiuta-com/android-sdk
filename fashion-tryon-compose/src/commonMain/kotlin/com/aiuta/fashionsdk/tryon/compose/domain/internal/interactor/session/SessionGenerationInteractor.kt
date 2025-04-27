package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.session

import coil3.PlatformContext
import com.aiuta.fashionsdk.configuration.features.tryon.history.AiutaTryOnGenerationsHistoryFeature
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImageUIModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.SessionImageUIModel

internal interface SessionGenerationInteractor {

    val sessionGenerations: List<SessionImageUIModel>

    fun deleteGenerations(images: List<GeneratedImageUIModel>)

    fun deleteGeneration(image: SessionImageUIModel)

    fun setFeedbackAsProvided(image: SessionImageUIModel)

    suspend fun addGeneration(image: GeneratedImageUIModel)

    suspend fun addGenerations(images: List<GeneratedImageUIModel>)

    companion object {
        fun getInstance(
            coilContext: PlatformContext,
            generationsHistoryFeature: AiutaTryOnGenerationsHistoryFeature?,
        ): SessionGenerationInteractor = when {
            generationsHistoryFeature != null -> GeneralSessionGenerationInteractor.getInstance(
                coilContext = coilContext,
            )
            else -> SingleImageSessionGenerationInteractor.getInstance(
                coilContext = coilContext,
            )
        }
    }
}

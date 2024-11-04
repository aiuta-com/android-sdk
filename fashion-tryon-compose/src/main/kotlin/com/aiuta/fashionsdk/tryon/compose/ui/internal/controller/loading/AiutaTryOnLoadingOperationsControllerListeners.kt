package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.loading

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.aiuta.fashionsdk.tryon.compose.domain.models.dataprovider.toGeneratedImage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

@Composable
internal fun AiutaTryOnLoadingActionsController.updateDeletingGeneratedImagesListener() {
    val aiutaConfiguration = LocalAiutaConfiguration.current
    val loadingActiveGenerations = loadingGenerationsHolder.getList()

    // Observe external changes of generated images and delete
    LaunchedEffect(Unit) {
        aiutaConfiguration
            .dataProvider
            ?.generatedImagesFlow
            ?.map { images ->
                // Make as list to compensate forEach with inner contains
                images.map { image -> image.toGeneratedImage() }.toSet()
            }
            ?.onEach { images ->
                loadingActiveGenerations.forEach { activeGeneration ->
                    if (!images.contains(activeGeneration)) {
                        loadingGenerationsHolder.remove(activeGeneration)
                    }
                }
            }
            ?.launchIn(generalScope)
    }
}

package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationOperation
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import kotlinx.coroutines.delay

private const val LOADING_TEXT_DELAY = 2000L

@Composable
internal fun solveLoadingGenerationText(): State<String> {
    val controller = LocalController.current
    val stringResources = LocalAiutaTryOnStringResources.current

    val operation = controller.generationOperations.lastOrNull()
    val loadingText = remember { mutableStateOf("") }

    LaunchedEffect(operation) {
        loadingText.value =
            when (operation) {
                is SKUGenerationOperation.LoadingOperation.GenerationProcessingOperation -> {
                    // Need to wait before showing ping status
                    delay(LOADING_TEXT_DELAY)
                    stringResources.imageSelectorGeneratingOutfit
                }

                is SKUGenerationOperation.LoadingOperation.UploadedSourceImageOperation -> {
                    stringResources.imageSelectorScanningBody
                }

                is SKUGenerationOperation.LoadingOperation.StartGenerationOperation -> {
                    stringResources.imageSelectorUploadingImage
                }

                else -> {
                    ""
                }
            }
    }

    return loadingText
}

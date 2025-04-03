package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.loading.AiutaTryOnLoadingPageFeature
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationOperation
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.strictProvideFeature
import kotlinx.coroutines.delay

private const val LOADING_TEXT_DELAY = 2000L

@Composable
internal fun solveLoadingGenerationText(): State<String> {
    val controller = LocalController.current

    val operation = controller.generationOperations.lastOrNull()
    val loadingText = remember { mutableStateOf("") }
    val loadingPageFeature = strictProvideFeature<AiutaTryOnLoadingPageFeature>()

    LaunchedEffect(operation) {
        loadingText.value =
            when (operation) {
                is SKUGenerationOperation.LoadingOperation.GenerationProcessingOperation -> {
                    // Need to wait before showing ping status
                    delay(LOADING_TEXT_DELAY)
                    loadingPageFeature.strings.tryOnLoadingStatusGeneratingOutfit
                }

                is SKUGenerationOperation.LoadingOperation.UploadedSourceImageOperation -> {
                    loadingPageFeature.strings.tryOnLoadingStatusScanningBody
                }

                is SKUGenerationOperation.LoadingOperation.StartGenerationOperation -> {
                    loadingPageFeature.strings.tryOnLoadingStatusUploadingImage
                }

                else -> {
                    loadingPageFeature.strings.tryOnLoadingStatusScanningBody
                }
            }
    }

    return loadingText
}

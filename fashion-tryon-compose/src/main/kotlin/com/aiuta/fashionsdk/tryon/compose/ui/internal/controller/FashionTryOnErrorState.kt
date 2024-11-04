package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller

import android.content.Context
import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.loading.AiutaTryOnLoadingActionsController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.utils.startGeneration
import kotlinx.coroutines.launch

@Immutable
internal interface ToastErrorState {
    val message: String?
    val onRetry: () -> Unit
}

@Immutable
internal class TryOnToastErrorState(
    aiutaConfiguration: AiutaTryOnConfiguration,
    context: Context,
    controller: FashionTryOnController,
) : ToastErrorState {
    override val message: String? = null
    override val onRetry: () -> Unit = {
        controller.startGeneration(
            aiutaConfiguration = aiutaConfiguration,
            context = context,
        )
    }
}

@Immutable
internal class DeleteGeneratedImagesToastErrorState(
    controller: FashionTryOnController,
    loadingActionsController: AiutaTryOnLoadingActionsController,
) : ToastErrorState {
    override val message: String? = null
    override val onRetry: () -> Unit = {
        loadingActionsController.generalScope.launch {
            val retryGenerations = loadingActionsController.loadingGenerationsHolder.getList()
            controller.generatedImageInteractor.remove(retryGenerations)
        }
    }
}

@Immutable
internal class DeleteUploadedImagesToastErrorState(
    controller: FashionTryOnController,
    loadingActionsController: AiutaTryOnLoadingActionsController,
) : ToastErrorState {
    override val message: String? = null
    override val onRetry: () -> Unit = {
        loadingActionsController.generalScope.launch {
            val retryOperations = loadingActionsController.loadingUploadsHolder.getList()
            controller.generatedOperationInteractor.deleteOperations(retryOperations)
        }
    }
}

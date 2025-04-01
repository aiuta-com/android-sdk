package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.loading

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import com.aiuta.fashionsdk.tryon.compose.domain.internal.selector.SelectedHolder
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImageUIModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperationUIModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Composable
internal fun rememberAiutaTryOnLoadingActionsController(): AiutaTryOnLoadingActionsController = remember { AiutaTryOnLoadingActionsController() }

@Immutable
internal class AiutaTryOnLoadingActionsController(
    // Generated images
    val loadingGenerationsHolder: SelectedHolder<GeneratedImageUIModel> = SelectedHolder(),
    val retryGenerationsHolder: SelectedHolder<GeneratedImageUIModel> = SelectedHolder(),
    // Uploaded images
    val loadingUploadsHolder: SelectedHolder<GeneratedOperationUIModel> = SelectedHolder(),
    val retryUploadsHolder: SelectedHolder<GeneratedOperationUIModel> = SelectedHolder(),
) {
    internal val generalScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
}

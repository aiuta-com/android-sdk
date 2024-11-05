package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.loading

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import com.aiuta.fashionsdk.tryon.compose.domain.internal.selector.SelectedHolder
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Composable
internal fun rememberAiutaTryOnLoadingActionsController(): AiutaTryOnLoadingActionsController {
    return remember { AiutaTryOnLoadingActionsController() }
}

@Immutable
internal class AiutaTryOnLoadingActionsController(
    // Generated images
    val loadingGenerationsHolder: SelectedHolder<GeneratedImage> = SelectedHolder(),
    val retryGenerationsHolder: SelectedHolder<GeneratedImage> = SelectedHolder(),
    // Uploaded images
    val loadingUploadsHolder: SelectedHolder<GeneratedOperation> = SelectedHolder(),
    val retryUploadsHolder: SelectedHolder<GeneratedOperation> = SelectedHolder(),
) {
    internal val generalScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
}

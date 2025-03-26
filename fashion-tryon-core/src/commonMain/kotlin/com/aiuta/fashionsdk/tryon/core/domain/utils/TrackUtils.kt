package com.aiuta.fashionsdk.tryon.core.domain.utils

import com.aiuta.fashionsdk.tryon.core.domain.AiutaTryOnImpl
import com.aiuta.fashionsdk.tryon.core.domain.analytic.sendErrorEvent
import com.aiuta.fashionsdk.tryon.core.domain.analytic.sendPublicTryOnErrorEvent
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationContainer
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.AiutaTryOnGenerationException
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.flow.FlowCollector

internal suspend fun <T> AiutaTryOnImpl.trackException(
    container: SKUGenerationContainer,
    action: suspend () -> T,
): T {
    return try {
        action()
    } catch (e: CancellationException) {
        // Just rethrow cancellation, because we don't want to track it
        throw e
    } catch (e: AiutaTryOnGenerationException) {
        analytic.sendErrorEvent(
            container = container,
            exception = e,
        )
        throw e
    } catch (e: Exception) {
        // Logging general exception
        analytic.sendPublicTryOnErrorEvent(
            container = container,
            errorMessage = e.message,
        )
        throw e
    }
}

internal suspend fun FlowCollector<SKUGenerationStatus>.errorListener(
    statusId: String,
    action: suspend () -> Unit,
) {
    try {
        action()
    } catch (e: Exception) {
        // Fallback with error
        emit(
            SKUGenerationStatus.ErrorGenerationStatus(
                statusId = statusId,
                errorMessage = e.message,
                exception = e,
            ),
        )
        throw e
    }
}

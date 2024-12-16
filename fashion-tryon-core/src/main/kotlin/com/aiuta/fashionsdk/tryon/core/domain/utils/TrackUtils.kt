package com.aiuta.fashionsdk.tryon.core.domain.utils

import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.tryon.core.domain.AiutaTryOnImpl
import com.aiuta.fashionsdk.tryon.core.domain.analytic.sendErrorEvent
import com.aiuta.fashionsdk.tryon.core.domain.analytic.sendFinishTryOnEvent
import com.aiuta.fashionsdk.tryon.core.domain.analytic.sendPublicTryOnErrorEvent
import com.aiuta.fashionsdk.tryon.core.domain.analytic.sendStartTryOnEvent
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationContainer
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.TryOnGenerationException
import kotlin.system.measureTimeMillis
import kotlinx.coroutines.flow.FlowCollector

internal suspend fun measureTryOn(
    analytic: InternalAiutaAnalytic,
    container: SKUGenerationContainer,
    action: suspend () -> Unit,
) {
    analytic.sendStartTryOnEvent(container)
    val loadingTimeMillis = measureTimeMillis { action() }
    analytic.sendFinishTryOnEvent(container, loadingTimeMillis)
}

internal suspend fun <T> AiutaTryOnImpl.trackException(
    container: SKUGenerationContainer,
    action: suspend () -> T,
): T {
    return try {
        action()
    } catch (e: TryOnGenerationException) {
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

internal suspend fun FlowCollector<SKUGenerationStatus>.errorListener(action: suspend () -> Unit) {
    try {
        action()
    } catch (e: Exception) {
        // Fallback with error
        emit(
            SKUGenerationStatus.ErrorGenerationStatus(
                errorMessage = e.message,
                exception = e,
            ),
        )
        throw e
    }
}

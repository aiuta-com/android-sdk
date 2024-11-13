package com.aiuta.fashionsdk.tryon.core.domain.utils

import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.model.TryOnError
import com.aiuta.fashionsdk.tryon.core.domain.analytic.sendFinishTryOnEvent
import com.aiuta.fashionsdk.tryon.core.domain.analytic.sendStartTryOnEvent
import com.aiuta.fashionsdk.tryon.core.domain.analytic.sendTryOnAbortedErrorEvent
import com.aiuta.fashionsdk.tryon.core.domain.analytic.sendTryOnErrorEvent
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationContainer
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.controller.exception.AbortedPingGenerationException
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

internal suspend fun <T> trackException(
    analytic: InternalAiutaAnalytic,
    type: TryOnError.Type,
    container: SKUGenerationContainer,
    action: suspend () -> T,
): T {
    return try {
        action()
    } catch (e: AbortedPingGenerationException) {
        // Logging aborted exception
        analytic.sendTryOnAbortedErrorEvent(
            type = type,
            container = container,
            errorMessage = e.message,
        )
        throw e
    } catch (e: Exception) {
        // Logging general exception
        analytic.sendTryOnErrorEvent(
            type = type,
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

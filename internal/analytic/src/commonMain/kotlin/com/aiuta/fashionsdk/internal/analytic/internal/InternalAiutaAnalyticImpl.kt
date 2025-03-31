package com.aiuta.fashionsdk.internal.analytic.internal

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.internal.updater.BaseUpdater
import com.aiuta.fashionsdk.internal.analytic.internal.worker.createAnalyticCompletedEvent
import com.aiuta.fashionsdk.internal.analytic.model.ExternalAnalyticEvent
import com.aiuta.fashionsdk.internal.analytic.model.InternalAnalyticEvent
import com.aiuta.fashionsdk.internal.analytic.utils.AnalyticConfig
import com.aiuta.fashionsdk.network.NetworkClient
import com.aiuta.fashionsdk.network.createNetworkClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class InternalAiutaAnalyticImpl(
    private val platformContext: AiutaPlatformContext,
    private val networkClient: NetworkClient,
) : InternalAiutaAnalytic, BaseUpdater() {
    private val _analyticFlow = MutableSharedFlow<ExternalAnalyticEvent?>(extraBufferCapacity = 10)
    override val analyticFlow: Flow<ExternalAnalyticEvent> = _analyticFlow.mapNotNull { it }

    private val scope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    override fun sendEvent(event: InternalAnalyticEvent) {
        // Add call of coroutine, because we need to emit new value with guarantees
        // Concurrency should not affect, because we use
        // BufferOverflow.SUSPEND strategy
        scope.launch {
            // Notify external listeners
            _analyticFlow.emit(event as? ExternalAnalyticEvent)

            resolveLog(event = event)
        }
    }

    private suspend fun resolveLog(event: InternalAnalyticEvent) {
        withContext(Dispatchers.IO) {
            retryAction {
                val completedEvent =
                    createAnalyticCompletedEvent(
                        platformContext = platformContext,
                        event = event,
                    )

                // Try to send analytic event
                networkClient.httpClient.value.post { setBody(completedEvent) }
            }
        }
    }

    companion object {
        fun getInstance(aiuta: Aiuta): InternalAiutaAnalytic {
            return InternalAiutaAnalyticImpl(
                platformContext = aiuta.platformContext,
                networkClient =
                    createNetworkClient(
                        aiuta = aiuta,
                        host = AnalyticConfig.DEFAULT_ENDPOINT,
                        encodedPath = AnalyticConfig.DEFAULT_ENCODED_PATH,
                    ),
            )
        }
    }
}

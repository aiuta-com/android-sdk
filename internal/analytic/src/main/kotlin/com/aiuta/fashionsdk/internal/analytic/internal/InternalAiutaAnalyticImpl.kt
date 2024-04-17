package com.aiuta.fashionsdk.internal.analytic.internal

import android.content.Context
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.workDataOf
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.internal.worker.AnalyticWorker
import com.aiuta.fashionsdk.internal.analytic.internal.worker.createAnalyticEnvironment
import com.aiuta.fashionsdk.internal.analytic.model.AnalyticEnvironment
import com.aiuta.fashionsdk.internal.analytic.model.InternalAnalyticEvent
import com.aiuta.fashionsdk.internal.analytic.model.ShareableAnalyticEvent
import com.aiuta.fashionsdk.internal.analytic.model.SharedAnalyticEvent
import com.aiuta.fashionsdk.internal.analytic.utils.AnalyticConfig
import com.aiuta.fashionsdk.network.NetworkClient
import com.aiuta.fashionsdk.network.createNetworkClient
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

internal class InternalAiutaAnalyticImpl(
    private val context: Context,
) : InternalAiutaAnalytic {
    private val _analyticFlow = MutableStateFlow<SharedAnalyticEvent?>(null)
    override val analyticFlow: Flow<SharedAnalyticEvent> = _analyticFlow.mapNotNull { it }

    override fun sendEvent(event: InternalAnalyticEvent) {
        resolveLog(
            event = event,
            params = emptyMap(),
        )
    }

    override fun sendEvent(
        event: InternalAnalyticEvent,
        params: Map<String, String?>,
    ) {
        resolveLog(
            event = event,
            params = params,
        )
    }

    override fun sendEvent(
        event: InternalAnalyticEvent,
        fillMap: MutableMap<String, String?>.() -> Unit,
    ) {
        val map = mutableMapOf<String, String?>()
        map.fillMap()
        resolveLog(
            event = event,
            params = map,
        )
    }

    private fun resolveLog(
        event: InternalAnalyticEvent,
        params: Map<String, String?>,
    ) {
        // Notify external listeners
        _analyticFlow.value = (event as? ShareableAnalyticEvent)?.toShared(params)

        // Build request
        val analyticWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<AnalyticWorker>()
                .setBackoffCriteria(
                    backoffPolicy = BackoffPolicy.EXPONENTIAL,
                    backoffDelay = WorkRequest.MIN_BACKOFF_MILLIS,
                    timeUnit = TimeUnit.MILLISECONDS,
                )
                .setInputData(
                    // Let's pass event name + all event params
                    workDataOf(
                        AnalyticWorker.EVENT_NAME_KEY to event.name,
                        *params
                            .map {
                                it.key to it.value
                            }
                            .toTypedArray(),
                    ),
                )
                .build()

        // Execute request
        WorkManager.getInstance(context).enqueue(analyticWorkRequest)
    }

    companion object {
        @Volatile
        private var instance: InternalAiutaAnalytic? = null
        private var analyticEnvironment: AnalyticEnvironment? = null
        private var cachedApiKey: String? = null
        var networkClient: NetworkClient? = null

        fun create(aiuta: Aiuta): InternalAiutaAnalytic {
            validateCacheInstance(newApiKey = aiuta.apiKey)

            return instance ?: synchronized(this) {
                instance ?: buildInternalAiutaAnalyticImpl(
                    context = aiuta.application,
                ).also {
                    instance = it
                    networkClient =
                        createNetworkClient(
                            apiKey = aiuta.apiKey,
                            backendEndpoint = AnalyticConfig.DEFAULT_ENDPOINT,
                        )
                    analyticEnvironment = createAnalyticEnvironment(aiuta.application)
                }
            }
        }

        private fun buildInternalAiutaAnalyticImpl(context: Context): InternalAiutaAnalytic {
            return InternalAiutaAnalyticImpl(
                context = context,
            )
        }

        private fun validateCacheInstance(newApiKey: String) {
            // We should remove cache, if we have new instance of api key
            if (newApiKey != cachedApiKey) {
                instance = null
                analyticEnvironment = null
                networkClient = null
                cachedApiKey = newApiKey
            }
        }
    }
}

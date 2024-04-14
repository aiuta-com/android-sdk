package com.aiuta.fashionsdk.analytic.internal

import android.content.Context
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.workDataOf
import com.aiuta.fashionsdk.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.analytic.internal.worker.AnalyticWorker
import com.aiuta.fashionsdk.analytic.internal.worker.createAnalyticEnvironment
import com.aiuta.fashionsdk.analytic.model.AnalyticEnvironment
import com.aiuta.fashionsdk.analytic.model.AnalyticEvent
import com.aiuta.fashionsdk.network.NetworkClient
import java.util.concurrent.TimeUnit

internal class InternalAiutaAnalyticImpl(
    private val context: Context,
) : InternalAiutaAnalytic {
    override fun sendEvent(event: AnalyticEvent) {
        resolveLog(
            event = event,
            params = emptyMap(),
        )
    }

    override fun sendEvent(
        event: AnalyticEvent,
        params: Map<String, String?>,
    ) {
        resolveLog(
            event = event,
            params = params,
        )
    }

    override fun sendEvent(
        event: AnalyticEvent,
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
        event: AnalyticEvent,
        params: Map<String, String?>,
    ) {
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
        var networkClient: NetworkClient? = null

        fun create(
            context: Context,
            networkClient: NetworkClient,
        ): InternalAiutaAnalytic {
            return instance ?: synchronized(this) {
                instance ?: buildInternalAiutaAnalyticImpl(
                    context = context,
                ).also {
                    this.instance = it
                    this.networkClient = networkClient
                    this.analyticEnvironment = createAnalyticEnvironment(context)
                }
            }
        }

        private fun buildInternalAiutaAnalyticImpl(context: Context): InternalAiutaAnalytic {
            return InternalAiutaAnalyticImpl(
                context = context,
            )
        }
    }
}

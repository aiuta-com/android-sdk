package com.aiuta.fashionsdk.internal.analytic.internal

import android.content.Context
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.workDataOf
import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.internal.worker.AnalyticWorker
import com.aiuta.fashionsdk.internal.analytic.model.ExternalAnalyticEvent
import com.aiuta.fashionsdk.internal.analytic.model.InternalAnalyticEvent
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class InternalAiutaAnalyticImpl(
    private val context: Context,
) : InternalAiutaAnalytic {
    private val _analyticFlow = MutableStateFlow<ExternalAnalyticEvent?>(null)
    override val analyticFlow: Flow<ExternalAnalyticEvent> = _analyticFlow.mapNotNull { it }

    override fun sendEvent(event: InternalAnalyticEvent) {
        resolveLog(event = event)
    }

    private fun resolveLog(event: InternalAnalyticEvent) {
        // Notify external listeners
        _analyticFlow.value = (event as? ExternalAnalyticEvent)

        // Build request
        val analyticWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<AnalyticWorker>()
                .setBackoffCriteria(
                    backoffPolicy = BackoffPolicy.EXPONENTIAL,
                    backoffDelay = WorkRequest.MIN_BACKOFF_MILLIS,
                    timeUnit = TimeUnit.MILLISECONDS,
                )
                .setInputData(
                    // Let's pass event data as json
                    workDataOf(
                        AnalyticWorker.EVENT_KEY to
                            Json.encodeToString<InternalAnalyticEvent>(
                                event,
                            ),
                    ),
                )
                .build()

        // Execute request
        WorkManager.getInstance(context).enqueue(analyticWorkRequest)
    }
}

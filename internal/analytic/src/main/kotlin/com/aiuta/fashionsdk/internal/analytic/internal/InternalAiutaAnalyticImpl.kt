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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class InternalAiutaAnalyticImpl(
    private val context: Context,
) : InternalAiutaAnalytic {
    private val _analyticFlow = MutableSharedFlow<ExternalAnalyticEvent?>(extraBufferCapacity = 10)
    override val analyticFlow: Flow<ExternalAnalyticEvent> = _analyticFlow.mapNotNull { it }

    private val scope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    override fun sendEvent(event: InternalAnalyticEvent) {
        // Add call of coroutine, because we need to emit new value with guarantees
        // Concurrency should not affect, because we use
        // BufferOverflow.SUSPEND strategy
        scope.launch { resolveLog(event = event) }
    }

    private suspend fun resolveLog(event: InternalAnalyticEvent) {
        // Notify external listeners
        _analyticFlow.emit(event as? ExternalAnalyticEvent)

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

package com.aiuta.fashionsdk.internal.analytic.internal.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.aiuta.fashionsdk.internal.analytic.internal.InternalAiutaAnalyticImpl
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class AnalyticWorker(
    private val context: Context,
    private val workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {
    private val internalCoroutineContext = Dispatchers.IO

    override suspend fun doWork(): Result {
        val networkClient = InternalAiutaAnalyticImpl.networkClient?.httpClient?.value ?: return Result.failure()

        return withContext(internalCoroutineContext) {
            try {
                // Build completed analytic event
                val completedEvent =
                    createAnalyticCompletedEvent(
                        context = context,
                        rawData = inputData,
                    )

                // Try to send analytic event
                completedEvent?.let {
                    networkClient.post { setBody(it) }

                    // Task complete successfully
                    Result.success()
                } ?: Result.failure()
            } catch (e: Exception) {
                // Task failed, should retry late
                Result.retry()
            }
        }
    }

    companion object {
        const val EVENT_NAME_KEY = "EVENT_NAME_KEY"
    }
}

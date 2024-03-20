package com.aiuta.fashionsdk.analytic.internal.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.aiuta.fashionsdk.analytic.internal.InternalAiutaAnalyticImpl
import io.ktor.client.request.get
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
                // TODO Delete
                Log.d("TAG_CHECK", "AnalyticWorker: send event - $completedEvent")

                // Try to send analytic event
                completedEvent?.let {
                    networkClient.post { setBody(it) }
                } ?: Result.failure()

                // Task complete successfully
                Result.success()
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

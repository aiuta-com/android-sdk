package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.warmup

import android.content.Context
import coil.imageLoader
import coil.request.ErrorResult
import coil.request.ImageRequest
import com.aiuta.fashionsdk.tryon.core.domain.models.policies.AiutaTryOnRetryPolicies
import com.aiuta.fashionsdk.tryon.core.domain.models.policies.DefaultAiutaTryOnRetryPolicies
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.AiutaTryOnExceptionType
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.AiutaTryOnGenerationException
import com.aiuta.fashionsdk.tryon.core.domain.utils.retryAction
import kotlinx.coroutines.delay

internal class WarmUpInteractor(
    private val context: Context,
    private val retryPolicies: AiutaTryOnRetryPolicies = DefaultAiutaTryOnRetryPolicies,
) {
    suspend fun warmUp(url: String) {
        retryAction(
            times = retryPolicies.resultDownloadRetryCount,
            delay = retryPolicies.retryDelay,
        ) {
            val request =
                ImageRequest.Builder(context)
                    .data(url)
                    .build()

            val result = context.imageLoader.execute(request)

            if (result is ErrorResult) {
                throw AiutaTryOnGenerationException(AiutaTryOnExceptionType.DOWNLOAD_RESULT_FAILED)
            }
        }
    }

    suspend fun saveWarmUp(url: String) {
        val request =
            ImageRequest.Builder(context)
                .data(url)
                .build()

        context.imageLoader.execute(request)
    }
}

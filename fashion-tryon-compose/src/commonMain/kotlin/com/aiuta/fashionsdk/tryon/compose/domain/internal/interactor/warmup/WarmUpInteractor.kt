package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.warmup

import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.request.ErrorResult
import coil3.request.ImageRequest
import com.aiuta.fashionsdk.tryon.core.domain.models.policies.AiutaTryOnRetryPolicies
import com.aiuta.fashionsdk.tryon.core.domain.models.policies.DefaultAiutaTryOnRetryPolicies
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.AiutaTryOnExceptionType
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.AiutaTryOnGenerationException
import com.aiuta.fashionsdk.tryon.core.domain.utils.retryAction

internal class WarmUpInteractor(
    private val context: PlatformContext,
    private val retryPolicies: AiutaTryOnRetryPolicies = DefaultAiutaTryOnRetryPolicies,
) {
    private val imageLoader by lazy { SingletonImageLoader.get(context) }

    suspend fun warmUp(url: String) {
        retryAction(
            times = retryPolicies.resultDownloadRetryCount,
            delay = retryPolicies.retryDelay,
        ) {
            val request =
                ImageRequest.Builder(context)
                    .data(url)
                    .build()

            val result = imageLoader.execute(request)

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

        imageLoader.execute(request)
    }
}

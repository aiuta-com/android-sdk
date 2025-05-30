package com.aiuta.fashionsdk.tryon.core.domain.utils

import com.aiuta.fashionsdk.tryon.core.domain.AiutaTryOnImpl
import com.aiuta.fashionsdk.tryon.core.domain.models.ProductGenerationContainer
import com.aiuta.fashionsdk.tryon.core.domain.models.policies.toRetryCount
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.AiutaTryOnExceptionType

internal suspend fun <T> AiutaTryOnImpl.trackTryOnArea(
    typeArea: AiutaTryOnExceptionType,
    container: ProductGenerationContainer,
    action: suspend () -> T,
): T = trackTryOnAreaExceptionWithRetryLambda(
    container = container,
    type = typeArea,
    retryAction = {
        retryAction(
            times = typeArea.toRetryCount(retryPolicies),
            delay = retryPolicies.retryDelay,
            action = action,
        )
    },
)

internal suspend fun <T> AiutaTryOnImpl.trackSpecificTryOnArea(
    typeArea: AiutaTryOnExceptionType,
    failingTypes: Set<AiutaTryOnExceptionType>,
    container: ProductGenerationContainer,
    action: suspend () -> T,
): T = trackTryOnAreaExceptionWithRetryLambda(
    container = container,
    type = typeArea,
    retryAction = {
        retryActionWithSpecificTypes(
            times = typeArea.toRetryCount(retryPolicies),
            failingTypes = failingTypes,
            action = action,
        )
    },
)

internal suspend fun <T> AiutaTryOnImpl.trackTryOnAreaExceptionWithRetryLambda(
    type: AiutaTryOnExceptionType,
    container: ProductGenerationContainer,
    retryAction: suspend () -> T,
): T = trackException(
    container = container,
    action = {
        tryOnExceptionArea(
            type = type,
            action = retryAction,
        )
    },
)

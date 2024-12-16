package com.aiuta.fashionsdk.tryon.core.domain.utils

import com.aiuta.fashionsdk.tryon.core.domain.AiutaTryOnImpl
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationContainer
import com.aiuta.fashionsdk.tryon.core.domain.models.policies.toRetryCount
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.TryOnExceptionType

internal suspend fun <T> AiutaTryOnImpl.trackTryOnArea(
    typeArea: TryOnExceptionType,
    container: SKUGenerationContainer,
    action: suspend () -> T,
): T {
    return trackTryOnAreaExceptionWithRetryLambda(
        container = container,
        type = typeArea,
        retryAction = {
            retryAction(
                times = typeArea.toRetryCount(retryPolicies),
                action = action,
            )
        },
    )
}

internal suspend fun <T> AiutaTryOnImpl.trackSpecificTryOnArea(
    typeArea: TryOnExceptionType,
    failingTypes: Set<TryOnExceptionType>,
    container: SKUGenerationContainer,
    action: suspend () -> T,
): T {
    return trackTryOnAreaExceptionWithRetryLambda(
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
}

internal suspend fun <T> AiutaTryOnImpl.trackTryOnAreaExceptionWithRetryLambda(
    type: TryOnExceptionType,
    container: SKUGenerationContainer,
    retryAction: suspend () -> T,
): T {
    return trackException(
        container = container,
        action = {
            tryOnExceptionArea(
                type = type,
                action = retryAction,
            )
        },
    )
}

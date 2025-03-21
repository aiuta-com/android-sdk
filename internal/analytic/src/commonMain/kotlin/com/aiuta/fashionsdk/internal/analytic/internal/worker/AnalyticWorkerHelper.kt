package com.aiuta.fashionsdk.internal.analytic.internal.worker

import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.internal.analytic.model.InternalAnalyticEvent
import com.aiuta.fashionsdk.internal.analytic.model.internal.AnalyticCompletedEvent
import com.aiuta.fashionsdk.internal.analytic.model.internal.AnalyticEnvironment
import com.aiuta.fashionsdk.internal.analytic.model.internal.currentLocalDateTime

internal expect suspend fun createAnalyticEnvironment(platformContext: AiutaPlatformContext): AnalyticEnvironment

internal suspend fun createAnalyticCompletedEvent(
    platformContext: AiutaPlatformContext,
    event: InternalAnalyticEvent,
): AnalyticCompletedEvent {
    return AnalyticCompletedEvent(
        data = event,
        environment = createAnalyticEnvironment(platformContext),
        localDateTime = currentLocalDateTime(),
    )
}

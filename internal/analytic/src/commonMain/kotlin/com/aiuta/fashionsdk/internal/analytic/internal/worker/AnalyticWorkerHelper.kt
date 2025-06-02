package com.aiuta.fashionsdk.internal.analytic.internal.worker

import com.aiuta.fashionsdk.analytics.events.AiutaAnalyticEvent
import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.internal.analytic.internal.model.AnalyticCompletedEvent
import com.aiuta.fashionsdk.internal.analytic.internal.model.AnalyticEnvironment
import com.aiuta.fashionsdk.internal.analytic.internal.model.currentLocalDateTime

internal expect suspend fun createAnalyticEnvironment(
    platformContext: AiutaPlatformContext,
): AnalyticEnvironment

internal suspend fun createAnalyticCompletedEvent(
    platformContext: AiutaPlatformContext,
    event: AiutaAnalyticEvent,
): AnalyticCompletedEvent = AnalyticCompletedEvent(
    data = event,
    environment = createAnalyticEnvironment(platformContext),
    localDateTime = currentLocalDateTime(),
)

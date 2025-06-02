package com.aiuta.fashionsdk.analytics.internal

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.analytics.AiutaAnalytics
import com.aiuta.fashionsdk.analytics.events.AiutaAnalyticsEvent
import com.aiuta.fashionsdk.internal.analytics.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytics.internalAiutaAnalytic
import kotlinx.coroutines.flow.Flow

internal class AiutaAnalyticsImpl(
    private val internalAnalytic: InternalAiutaAnalytic,
) : AiutaAnalytics {
    override val analyticFlow: Flow<AiutaAnalyticsEvent> = internalAnalytic.analyticFlow

    companion object {
        fun create(aiuta: Aiuta): AiutaAnalytics = AiutaAnalyticsImpl(
            internalAnalytic = aiuta.internalAiutaAnalytic,
        )
    }
}

package com.aiuta.fashionsdk.analytics.internal

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.analytics.AiutaAnalytic
import com.aiuta.fashionsdk.analytics.events.AiutaAnalyticsEvent
import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.internalAiutaAnalytic
import kotlinx.coroutines.flow.Flow

internal class AiutaAnalyticImpl(
    private val internalAnalytic: InternalAiutaAnalytic,
) : AiutaAnalytic {
    override val analyticFlow: Flow<AiutaAnalyticsEvent> = internalAnalytic.analyticFlow

    companion object {
        fun create(aiuta: Aiuta): AiutaAnalytic = AiutaAnalyticImpl(
            internalAnalytic = aiuta.internalAiutaAnalytic,
        )
    }
}

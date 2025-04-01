package com.aiuta.fashionsdk.analytic.internal

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.analytic.AiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.internalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.model.ExternalAnalyticEvent
import kotlinx.coroutines.flow.Flow

internal class AiutaAnalyticImpl(
    private val internalAnalytic: InternalAiutaAnalytic,
) : AiutaAnalytic {
    override val analyticFlow: Flow<ExternalAnalyticEvent> = internalAnalytic.analyticFlow

    companion object {
        fun create(aiuta: Aiuta): AiutaAnalytic = AiutaAnalyticImpl(
            internalAnalytic = aiuta.internalAiutaAnalytic,
        )
    }
}

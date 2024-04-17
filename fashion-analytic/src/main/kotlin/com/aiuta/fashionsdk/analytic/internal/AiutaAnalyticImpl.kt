package com.aiuta.fashionsdk.analytic.internal

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.analytic.AiutaAnalytic
import com.aiuta.fashionsdk.analytic.events.AnalyticEvent
import com.aiuta.fashionsdk.analytic.events.toPublic
import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.internalAiutaAnalytic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class AiutaAnalyticImpl(
    private val internalAnalytic: InternalAiutaAnalytic,
) : AiutaAnalytic {
    override val analyticFlow: Flow<AnalyticEvent> =
        internalAnalytic
            .analyticFlow
            .map { it.toPublic() }

    companion object {
        fun create(aiuta: Aiuta): AiutaAnalytic {
            return AiutaAnalyticImpl(
                internalAnalytic = aiuta.internalAiutaAnalytic,
            )
        }
    }
}

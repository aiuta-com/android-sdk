package com.aiuta.fashionsdk.tryon.compose.domain.internal.analytic.configure

import com.aiuta.fashionsdk.internal.analytic.model.ConfigureEvent
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.AiutaTryOnConfiguration

internal fun AiutaTryOnConfiguration.sendConfigurationEvent() {
    aiutaAnalytic.sendEvent(
        event =
            ConfigureEvent(),
    )
}

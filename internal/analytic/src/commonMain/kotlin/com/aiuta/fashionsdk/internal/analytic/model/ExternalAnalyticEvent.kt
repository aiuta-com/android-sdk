package com.aiuta.fashionsdk.internal.analytic.model

import kotlinx.serialization.json.Json

public sealed interface ExternalAnalyticEvent : InternalAnalyticEvent {
    public fun serialize(): String = Json.encodeToString<InternalAnalyticEvent>(this)
}

package com.aiuta.fashionsdk.internal.analytic.internal.model

import com.aiuta.fashionsdk.analytics.events.AiutaAnalyticEvent
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AnalyticCompletedEvent(
    @SerialName("data")
    val data: AiutaAnalyticEvent,
    @SerialName("env")
    val environment: AnalyticEnvironment,
    @SerialName("localDateTime")
    val localDateTime: String,
)

internal fun currentLocalDateTime(): String {
    val currentInstant = Clock.System.now()
    val currentDateTime = currentInstant.toLocalDateTime(TimeZone.currentSystemDefault())
    return currentDateTime.toString()
}

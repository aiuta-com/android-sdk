package com.aiuta.fashionsdk.internal.analytic.model.internal

import com.aiuta.fashionsdk.internal.analytic.model.InternalAnalyticEvent
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AnalyticCompletedEvent(
    @SerialName("data")
    val data: InternalAnalyticEvent,
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

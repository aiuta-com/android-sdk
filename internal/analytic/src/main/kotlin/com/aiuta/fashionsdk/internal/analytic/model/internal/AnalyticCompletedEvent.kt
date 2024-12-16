package com.aiuta.fashionsdk.internal.analytic.model.internal

import com.aiuta.fashionsdk.internal.analytic.model.InternalAnalyticEvent
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
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
    val locale = Locale.getDefault()
    val iso8601Pattern = "yyyy-MM-dd'T'HH:mm:ssZ"
    val format = SimpleDateFormat(iso8601Pattern, locale)
    return format.format(Date())
}

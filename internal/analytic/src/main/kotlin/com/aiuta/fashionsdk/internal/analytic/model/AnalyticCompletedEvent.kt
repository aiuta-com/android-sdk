package com.aiuta.fashionsdk.internal.analytic.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AnalyticCompletedEvent(
    @SerialName("event")
    val event: CompletedInternalAnalyticEvent,
    @SerialName("env")
    val environment: AnalyticEnvironment,
    @SerialName("local_date_time")
    val localDateTime: String,
)

internal fun currentLocalDateTime(): String {
    val locale = Locale.getDefault()
    val iso8601Pattern = "yyyy-MM-dd'T'HH:mm:ssZ"
    val format = SimpleDateFormat(iso8601Pattern, locale)
    return format.format(Date())
}

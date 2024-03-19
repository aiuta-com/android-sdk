package com.aiuta.fashionsdk.analytic.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class AnalyticCompletedEvent(
    @SerialName("event")
    val event: InternalAnalyticEvent,
    @SerialName("env")
    val environment: AnalyticEnvironment,
    @SerialName("local_date_time")
    val localDateTime: String = currentLocalDateTime(),
)

private fun currentLocalDateTime(): String {
    val locale = Locale.getDefault()
    val iso8601Pattern = "yyyy-MM-dd'T'HH:mm:ssZ"
    val format = SimpleDateFormat(iso8601Pattern, locale)
    return format.format(Date())
}

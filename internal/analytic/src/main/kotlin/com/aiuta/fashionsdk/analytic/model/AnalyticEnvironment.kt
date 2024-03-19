package com.aiuta.fashionsdk.analytic.model

import com.aiuta.fashionsdk.analytic.BuildConfig
import com.aiuta.fashionsdk.analytic.utils.AnalyticConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class AnalyticEnvironment(
    @SerialName("platform")
    val platform: String = AnalyticConfig.DEFAULT_PLATFORM,
    @SerialName("sdk_version")
    val sdkVersion: String = BuildConfig.VERSION_NAME,
    @SerialName("host_id")
    val hostId: String,
    @SerialName("host_version")
    val hostVersion: String?,
) {
    companion object {
        val DEFAULT =
            AnalyticEnvironment(
                hostId = AnalyticConfig.DEFAULT_HOST_ID,
                hostVersion = null,
            )
    }
}

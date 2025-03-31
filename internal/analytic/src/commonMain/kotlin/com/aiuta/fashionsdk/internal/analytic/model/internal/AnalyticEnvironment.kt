package com.aiuta.fashionsdk.internal.analytic.model.internal

import com.aiuta.fashionsdk.internal.analytic.utils.AnalyticConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class AnalyticEnvironment(
    @SerialName("platform")
    val platform: String,
    @SerialName("sdkVersion")
    val sdkVersion: String,
    @SerialName("hostId")
    val hostId: String,
    @SerialName("hostVersion")
    val hostVersion: String?,
    @SerialName("installationId")
    val installationId: String?,
) {
    companion object {
        val DEFAULT =
            AnalyticEnvironment(
                platform = AnalyticConfig.DEFAULT_PLATFORM,
                sdkVersion = AnalyticConfig.DEFAULT_SDK_VERSION,
                hostId = AnalyticConfig.DEFAULT_HOST_ID,
                hostVersion = null,
                installationId = null,
            )
    }
}

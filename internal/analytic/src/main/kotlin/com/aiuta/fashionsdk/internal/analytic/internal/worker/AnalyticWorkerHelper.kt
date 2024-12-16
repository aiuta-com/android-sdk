package com.aiuta.fashionsdk.internal.analytic.internal.worker

import android.content.Context
import androidx.work.Data
import com.aiuta.fashionsdk.internal.analytic.BuildConfig
import com.aiuta.fashionsdk.internal.analytic.internal.installation.Installation
import com.aiuta.fashionsdk.internal.analytic.model.InternalAnalyticEvent
import com.aiuta.fashionsdk.internal.analytic.model.internal.AnalyticCompletedEvent
import com.aiuta.fashionsdk.internal.analytic.model.internal.AnalyticEnvironment
import com.aiuta.fashionsdk.internal.analytic.model.internal.currentLocalDateTime
import com.aiuta.fashionsdk.internal.analytic.utils.AnalyticConfig
import kotlinx.serialization.json.Json

internal suspend fun createAnalyticEnvironment(context: Context): AnalyticEnvironment {
    return try {
        val packageName = context.packageName ?: AnalyticConfig.DEFAULT_HOST_ID
        val packageInfo = context.packageManager.getPackageInfo(packageName, 0)

        AnalyticEnvironment(
            platform = AnalyticConfig.DEFAULT_PLATFORM,
            sdkVersion = BuildConfig.VERSION_NAME,
            hostId = packageName,
            hostVersion = packageInfo.versionName,
            installationId = Installation.id(context),
        )
    } catch (e: Exception) {
        // Fallback to default
        AnalyticEnvironment.DEFAULT
    }
}

internal suspend fun createAnalyticCompletedEvent(
    context: Context,
    rawData: Data,
): AnalyticCompletedEvent? {
    return rawData.getString(AnalyticWorker.EVENT_KEY)?.let { rawEvent ->
        // Remove event name key
        val event = Json.decodeFromString<InternalAnalyticEvent>(rawEvent)

        AnalyticCompletedEvent(
            data = event,
            environment = createAnalyticEnvironment(context),
            localDateTime = currentLocalDateTime(),
        )
    }
}

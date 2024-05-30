package com.aiuta.fashionsdk.internal.analytic.internal.worker

import android.content.Context
import androidx.work.Data
import com.aiuta.fashionsdk.internal.analytic.internal.installation.Installation
import com.aiuta.fashionsdk.internal.analytic.model.AnalyticCompletedEvent
import com.aiuta.fashionsdk.internal.analytic.model.AnalyticEnvironment
import com.aiuta.fashionsdk.internal.analytic.model.CompletedInternalAnalyticEvent
import com.aiuta.fashionsdk.internal.analytic.utils.AnalyticConfig

internal suspend fun createAnalyticEnvironment(context: Context): AnalyticEnvironment {
    return try {
        val packageName = context.packageName ?: AnalyticConfig.DEFAULT_HOST_ID
        val packageInfo = context.packageManager.getPackageInfo(packageName, 0)

        AnalyticEnvironment(
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
    return rawData.getString(AnalyticWorker.EVENT_NAME_KEY)?.let { eventName ->
        // Remove event name key
        val filteredMap =
            rawData.keyValueMap
                .filter { it.key != AnalyticWorker.EVENT_NAME_KEY }
                .mapValues { it.value?.toString() }

        AnalyticCompletedEvent(
            event =
                CompletedInternalAnalyticEvent(
                    name = eventName,
                    params = filteredMap,
                ),
            environment = createAnalyticEnvironment(context),
        )
    }
}

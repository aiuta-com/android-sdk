package com.aiuta.fashionsdk.internal.analytic.internal.worker

import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.internal.analytic.BuildKonfig
import com.aiuta.fashionsdk.internal.analytic.internal.installation.Installation
import com.aiuta.fashionsdk.internal.analytic.internal.model.AnalyticEnvironment
import com.aiuta.fashionsdk.internal.analytic.internal.utils.AnalyticConfig

internal actual suspend fun createAnalyticEnvironment(
    platformContext: AiutaPlatformContext,
): AnalyticEnvironment = try {
    val hostId = platformContext.packageName ?: AnalyticConfig.DEFAULT_HOST_ID
    val packageInfo = platformContext.packageManager.getPackageInfo(hostId, 0)

    AnalyticEnvironment(
        platform = AnalyticConfig.PLATFORM_ANDROID,
        sdkVersion = BuildKonfig.VERSION_NAME,
        hostId = hostId,
        hostVersion = packageInfo.versionName,
        installationId = Installation.id(platformContext),
    )
} catch (e: Exception) {
    // Fallback to default
    AnalyticEnvironment.DEFAULT
}

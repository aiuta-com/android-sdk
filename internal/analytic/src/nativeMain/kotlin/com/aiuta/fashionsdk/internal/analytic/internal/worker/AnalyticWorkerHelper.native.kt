package com.aiuta.fashionsdk.internal.analytic.internal.worker

import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.internal.analytic.BuildKonfig
import com.aiuta.fashionsdk.internal.analytic.internal.installation.Installation
import com.aiuta.fashionsdk.internal.analytic.internal.model.AnalyticEnvironment
import com.aiuta.fashionsdk.internal.analytic.internal.utils.AnalyticConfig
import platform.Foundation.NSBundle

internal actual suspend fun createAnalyticEnvironment(
    platformContext: AiutaPlatformContext,
): AnalyticEnvironment = try {
    val hostId = NSBundle.mainBundle.bundleIdentifier ?: AnalyticConfig.DEFAULT_HOST_ID
    val hostVersion =
        NSBundle.mainBundle.objectForInfoDictionaryKey(
            "CFBundleShortVersionString",
        ) as? String ?: AnalyticConfig.DEFAULT_HOST_VERSION

    AnalyticEnvironment(
        platform = AnalyticConfig.PLATFORM_IOS,
        sdkVersion = BuildKonfig.VERSION_NAME,
        hostId = hostId,
        hostVersion = hostVersion,
        installationId = Installation.id(platformContext),
    )
} catch (e: Exception) {
    // Fallback to default
    AnalyticEnvironment.DEFAULT
}

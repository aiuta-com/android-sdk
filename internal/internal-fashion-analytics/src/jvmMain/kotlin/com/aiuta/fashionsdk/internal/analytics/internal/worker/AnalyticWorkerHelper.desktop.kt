package com.aiuta.fashionsdk.internal.analytics.internal.worker

import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.internal.analytic.BuildKonfig
import com.aiuta.fashionsdk.internal.analytics.internal.installation.Installation
import com.aiuta.fashionsdk.internal.analytics.internal.installation.utils.DesktopSystemType
import com.aiuta.fashionsdk.internal.analytics.internal.installation.utils.solveDesktopSystemType
import com.aiuta.fashionsdk.internal.analytics.internal.model.AnalyticEnvironment
import com.aiuta.fashionsdk.internal.analytics.internal.utils.AnalyticConfig

internal actual suspend fun createAnalyticEnvironment(
    platformContext: AiutaPlatformContext,
): AnalyticEnvironment = try {
    val hostId = AnalyticConfig.DEFAULT_HOST_ID
    val hostVersion = AnalyticConfig.DEFAULT_HOST_VERSION

    AnalyticEnvironment(
        platform = solveDesktopSystemType().toAnalyticPlatform(),
        sdkVersion = BuildKonfig.VERSION_NAME,
        hostId = hostId,
        hostVersion = hostVersion,
        installationId = Installation.id(platformContext),
    )
} catch (e: Exception) {
    AnalyticEnvironment.DEFAULT
}

private fun DesktopSystemType.toAnalyticPlatform() = when (this) {
    is DesktopSystemType.Linux -> AnalyticConfig.PLATFORM_LINUX
    is DesktopSystemType.MacOs -> AnalyticConfig.PLATFORM_MACOS
    is DesktopSystemType.Windows -> AnalyticConfig.PLATFORM_WINDOWS
}

package com.aiuta.fashionsdk.internal.analytic.internal.installation

import com.aiuta.fashionsdk.context.AiutaPlatformContext

internal interface InstallationStorage {
    suspend fun readInstallationId(): String?

    suspend fun writeInstallationId(id: String)
}

internal expect fun buildInstallationStorage(
    platformContext: AiutaPlatformContext,
): InstallationStorage

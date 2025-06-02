package com.aiuta.fashionsdk.internal.analytics.internal.installation

import com.aiuta.fashionsdk.context.AiutaPlatformContext

internal const val INSTALLATION_FILE_NAME = "INSTALLATION.txt"
internal const val ANDROID_INSTALLATION_FILE_NAME = "INSTALLATION"

internal interface InstallationStorage {
    suspend fun readInstallationId(): String?

    suspend fun writeInstallationId(id: String)
}

internal expect fun buildInstallationStorage(
    platformContext: AiutaPlatformContext,
): InstallationStorage

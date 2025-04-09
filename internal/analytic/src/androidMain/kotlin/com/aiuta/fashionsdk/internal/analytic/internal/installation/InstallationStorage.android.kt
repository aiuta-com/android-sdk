package com.aiuta.fashionsdk.internal.analytic.internal.installation

import com.aiuta.fashionsdk.context.AiutaPlatformContext
import java.io.File
import java.io.FileOutputStream
import java.io.RandomAccessFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal actual fun buildInstallationStorage(
    platformContext: AiutaPlatformContext,
): InstallationStorage = AndroidInstallationStorage(platformContext)

internal class AndroidInstallationStorage(
    private val platformContext: AiutaPlatformContext,
) : InstallationStorage {
    private val fileName = "INSTALLATION"

    override suspend fun readInstallationId(): String? {
        val file = File(platformContext.application.filesDir, fileName)
        if (!file.exists()) return null

        return withContext(Dispatchers.IO) {
            RandomAccessFile(file, "r").use { raf ->
                val bytes = ByteArray(raf.length().toInt())
                raf.readFully(bytes)
                String(bytes)
            }
        }
    }

    override suspend fun writeInstallationId(id: String) {
        val file = File(platformContext.application.filesDir, fileName)
        withContext(Dispatchers.IO) {
            FileOutputStream(file).use {
                it.write(id.toByteArray())
            }
        }
    }
}

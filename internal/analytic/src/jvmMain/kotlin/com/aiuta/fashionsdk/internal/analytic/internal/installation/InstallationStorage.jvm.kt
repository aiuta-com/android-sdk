package com.aiuta.fashionsdk.internal.analytic.internal.installation

import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.internal.analytic.internal.installation.utils.solveDesktopSystemType
import java.io.File
import java.io.FileOutputStream
import java.io.RandomAccessFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal actual fun buildInstallationStorage(
    platformContext: AiutaPlatformContext,
): InstallationStorage = JvmInstallationStorage()

internal class JvmInstallationStorage : InstallationStorage {

    private val fileName = "INSTALLATION"
    private val desktopType by lazy { solveDesktopSystemType() }
    private val baseDir by lazy { File(desktopType.platformPath()) }

    override suspend fun readInstallationId(): String? {
        val file = File(baseDir, fileName)
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
        val file = File(baseDir, fileName)
        if (!baseDir.exists()) baseDir.mkdirs()
        withContext(Dispatchers.IO) {
            FileOutputStream(file).use {
                it.write(id.toByteArray())
            }
        }
    }
}

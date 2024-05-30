package com.aiuta.fashionsdk.internal.analytic.internal.installation

import android.content.Context
import io.ktor.utils.io.core.use
import java.io.File
import java.io.FileOutputStream
import java.io.RandomAccessFile
import java.util.UUID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

internal object Installation {
    private val mutex = Mutex()
    private var installationId: String? = null
    private const val INSTALLATION = "INSTALLATION"

    suspend fun id(context: Context): String? {
        return mutex.withLock {
            withContext(Dispatchers.IO) {
                if (installationId == null) {
                    val installation = File(context.filesDir, INSTALLATION)

                    installationId =
                        try {
                            // Create new installation file
                            if (!installation.exists()) {
                                writeInstallationFile(installation)
                            }

                            // Read
                            readInstallationFile(installation)
                        } catch (e: Exception) {
                            // Failed to solve installation file
                            null
                        }
                }

                installationId
            }
        }
    }

    private fun readInstallationFile(installation: File): String {
        val file = RandomAccessFile(installation, "r")
        val bytes = ByteArray(file.length().toInt())

        file.use {
            it.readFully(bytes)
        }

        return String(bytes)
    }

    private fun writeInstallationFile(installation: File) {
        val file = FileOutputStream(installation)
        val id: String = UUID.randomUUID().toString()

        file.use {
            it.write(id.toByteArray())
        }
    }
}

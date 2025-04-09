package com.aiuta.fashionsdk.internal.analytic.internal.installation

import com.aiuta.fashionsdk.context.AiutaPlatformContext
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal object Installation {
    private val mutex = Mutex()
    private var installationId: String? = null
    private var storage: InstallationStorage? = null

    suspend fun id(platformContext: AiutaPlatformContext): String = mutex.withLock {
        val currentInstallationId = installationId

        if (currentInstallationId == null) {
            val currentStorage = initStorage(platformContext)

            val solvedInstallationId =
                try {
                    val savedInstallationId = storage?.readInstallationId()

                    checkNotNull(savedInstallationId)

                    savedInstallationId
                } catch (e: Exception) {
                    generateAndStoreId(currentStorage)
                }

            // Just update id
            installationId = solvedInstallationId

            solvedInstallationId
        } else {
            currentInstallationId
        }
    }

    private fun initStorage(
        platformContext: AiutaPlatformContext,
    ): InstallationStorage = storage ?: buildInstallationStorage(platformContext).also {
        storage = it
    }

    private suspend fun generateAndStoreId(storage: InstallationStorage): String {
        val id = generateUUID()

        try {
            storage.writeInstallationId(id)
        } catch (e: Exception) {
            // Failed to save id
        }

        return id
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun generateUUID(): String = Uuid.random().toString()
}

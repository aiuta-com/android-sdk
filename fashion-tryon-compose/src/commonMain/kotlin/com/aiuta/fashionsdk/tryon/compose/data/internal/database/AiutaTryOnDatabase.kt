package com.aiuta.fashionsdk.tryon.compose.data.internal.database

import app.cash.sqldelight.EnumColumnAdapter
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.builder.createSQLDelightDriver
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.converters.ListStringsAdapter
import kotlin.concurrent.Volatile
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.withContext

internal const val AIUTA_TRYON_DATABASE_NAME = "aiuta-tryon-database.db"

internal object AiutaTryOnDatabaseFactory : SynchronizedObject() {

    @Volatile
    private var instance: AiutaTryOnDatabase? = null
    private val mutex = Mutex()

    fun getInstance(): AiutaTryOnDatabase = checkNotNull(instance) {
        "Database should be init before getting operation"
    }

    suspend fun createDatabase(aiuta: Aiuta) {
        if (instance != null) return
        mutex.lock()
        if (instance != null) return

        // Init
        val sqlDriver = createSQLDelightDriver(
            platformContext = aiuta.platformContext,
            schema = AiutaTryOnDatabase.Schema,
        )
        instance = AiutaTryOnDatabase(
            driver = sqlDriver,
            generated_imageAdapter = Generated_image.Adapter(
                typeAdapter = EnumColumnAdapter(),
                productIdsAdapter = ListStringsAdapter(),
            ),
            tryon_modelAdapter = Tryon_model.Adapter(
                typeAdapter = EnumColumnAdapter(),
            ),
        )

        // Check
        validateCache(aiuta)

        mutex.unlock()
    }

    private suspend fun validateCache(aiuta: Aiuta) {
        withContext(Dispatchers.IO) {
            val database = getInstance()
            val aiutaCodeQueries = database.aiutaCodeQueries
            val cachedSubscriptionId = aiutaCodeQueries
                .selectAll()
                .executeAsList()
                .firstOrNull()

            // Invalidate all records, if we have new Aiuta instance
            if (cachedSubscriptionId != aiuta.authenticationStrategy.subscriptionId) {
                database.transaction {
                    if (cachedSubscriptionId != null) {
                        // Delete all records
                        // TODO
                        // database.generatedOperationDao().removeAll()
                        // database.generatedImageDao().removeAll()
                        // database.sourceImageDao().removeAll()
                    }

                    // Update code
                    aiutaCodeQueries.removeAll()
                    aiutaCodeQueries.insert(
                        subscription_id = aiuta.authenticationStrategy.subscriptionId,
                    )
                }
            }
        }
    }
}

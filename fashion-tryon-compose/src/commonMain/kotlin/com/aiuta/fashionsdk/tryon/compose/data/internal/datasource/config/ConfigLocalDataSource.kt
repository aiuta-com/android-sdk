package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.config

import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AiutaTryOnDatabase
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AiutaTryOnDatabaseFactory
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.config.local.TryOnCategoryLocalDataSource
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.ClientConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class ConfigLocalDataSource(
    private val database: AiutaTryOnDatabase,
    private val tryOnCategoryLocalDataSource: TryOnCategoryLocalDataSource,
) {
    private val configMetaQueries by lazy { database.configMetaQueries }

    suspend fun getEtag(): String? = withContext(Dispatchers.IO) {
        configMetaQueries.select().executeAsOne()
    }

    suspend fun getBackendConfig(): ClientConfig? = withContext(Dispatchers.IO) {
        ClientConfig(
            etag = getEtag(),
            predefinedTryOnModels = tryOnCategoryLocalDataSource.getCategories(),
        )
    }

    suspend fun replaceConfig(newConfig: ClientConfig) = withContext(Dispatchers.IO) {
        database.transaction {
            // Delete old
            configMetaQueries.removeAll()

            // Replace
            newConfig.etag?.let { configMetaQueries.insert(etag = it) }
            newConfig.predefinedTryOnModels?.let {
                tryOnCategoryLocalDataSource.replaceCategories(
                    newCategories = it,
                )
            }
        }
    }

    companion object {
        fun getInstance(): ConfigLocalDataSource = ConfigLocalDataSource(
            database = AiutaTryOnDatabaseFactory.getInstance(),
            tryOnCategoryLocalDataSource = TryOnCategoryLocalDataSource.getInstance(),
        )
    }
}

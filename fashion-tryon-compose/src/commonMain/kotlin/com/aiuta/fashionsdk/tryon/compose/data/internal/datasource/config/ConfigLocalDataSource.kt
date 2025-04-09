package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.config

import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AppDatabase
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.config.dao.ConfigDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.config.dao.replaceAll
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.config.toDTO
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.config.toEntity
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.ClientConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class ConfigLocalDataSource(
    private val configDao: ConfigDao,
) {
    suspend fun getEtag(): String? = withContext(Dispatchers.IO) {
        configDao.getAllEtags(limit = 1).firstOrNull()
    }

    suspend fun getBackendConfig(): ClientConfig? = withContext(Dispatchers.IO) {
        configDao.getAll(limit = 1).firstOrNull()?.toDTO()
    }

    suspend fun replaceConfig(newConfig: ClientConfig) = withContext(Dispatchers.IO) {
        configDao.replaceAll(newConfig.toEntity())
    }

    companion object {
        fun getInstance(platformContext: AiutaPlatformContext): ConfigLocalDataSource = ConfigLocalDataSource(
            configDao = AppDatabase.getInstance(platformContext).configDao(),
        )
    }
}
